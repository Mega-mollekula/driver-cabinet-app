package com.example.drivercabinet.presentation.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.drivercabinet.R
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.DrivingOptions
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingSession
import com.yandex.mapkit.directions.driving.VehicleOptions
import com.yandex.runtime.Error

data class Order(
    val startLocation: String,
    val endLocation: String,
    val cargoType: String
)

@Composable
fun OrdersScreen() {
    val orders = listOf(
        Order("Москва, ул. Ленина, 10", "Санкт-Петербург, Невский пр-т, 20", "Электроника"),
        Order("Казань, ул. Баумана, 5", "Екатеринбург, ул. Малышева, 15", "Мебель"),
        Order("Сочи, ул. Навагинская, 7", "Краснодар, ул. Красная, 100", "Скоропортящиеся товары"),
        Order("Новосибирск, ул. Советская, 25", "Томск, пр-т Ленина, 50", "Экстренный груз")
    )

    var anyMapVisible by remember { mutableStateOf(false) }
    val scrollState = rememberLazyListState()

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        try {
            MapKitFactory.initialize(context)
            MapKitFactory.getInstance().onStart()
        } catch (e: Exception) {
            Log.e("MapKit", "Failed to initialize MapKit: ${e.message}")
            Toast.makeText(context, "Ошибка инициализации карты", Toast.LENGTH_LONG).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(orders) { order ->
                OrderCard(order) { isVisible ->
                    anyMapVisible = isVisible
                }
            }
        }
    }
}

@Composable
fun OrderCard(order: Order, onMapVisibilityChange: (Boolean) -> Unit) {
    var isMapVisible by remember { mutableStateOf(false) }

    Card {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Место старта: ${order.startLocation}")
            Text(text = "Адрес конечной локации: ${order.endLocation}")
            Text(text = "Груз: ${order.cargoType}")

            if (isMapVisible) {
                Box(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .pointerInput(Unit) {
                            detectDragGestures { _, _ -> }
                        }
                ) {
                    MapViewComposable(
                        startLocation = order.startLocation,
                        endLocation = order.endLocation,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Button(
                onClick = {
                    isMapVisible = !isMapVisible
                    onMapVisibilityChange(isMapVisible)
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(if (isMapVisible) "Скрыть карту" else "Открыть карту")
            }
        }
    }
}

@Composable
fun MapViewComposable(
    startLocation: String,
    endLocation: String,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var mapLoadError by remember { mutableStateOf(false) }

    val mapView = remember {
        MapView(context).apply {
            map.move(
                CameraPosition(Point(55.751244, 37.618423), 10.0f, 0.0f, 0.0f)
            )
            map.isZoomGesturesEnabled = true
            map.isScrollGesturesEnabled = true
            map.isRotateGesturesEnabled = true
        }
    }

    val startPoint = when (startLocation) {
        "Москва, ул. Ленина, 10" -> Point(55.7558, 37.6173)
        "Казань, ул. Баумана, 5" -> Point(55.7963, 49.1064)
        "Сочи, ул. Навагинская, 7" -> Point(43.6028, 39.7342)
        "Новосибирск, ул. Советская, 25" -> Point(55.0084, 82.9357)
        else -> Point(55.751244, 37.618423)
    }

    val endPoint = when (endLocation) {
        "Санкт-Петербург, Невский пр-т, 20" -> Point(59.9343, 30.3351)
        "Екатеринбург, ул. Малышева, 15" -> Point(56.8389, 60.6057)
        "Краснодар, ул. Красная, 100" -> Point(45.0355, 38.9753)
        "Томск, пр-т Ленина, 50" -> Point(56.4977, 84.9744)
        else -> Point(55.751244, 37.618423)
    }

    val drivingRouter = remember { DirectionsFactory.getInstance().createDrivingRouter() }
    val mapObjects = remember { mapView.map.mapObjects }

    DisposableEffect(lifecycleOwner, startPoint, endPoint) {
        mapView.onStart()

        mapObjects.clear()

        val requestPoints = listOf(
            RequestPoint(startPoint, RequestPointType.WAYPOINT, null),
            RequestPoint(endPoint, RequestPointType.WAYPOINT, null)
        )

        val drivingOptions = DrivingOptions().apply { routesCount = 1 }
        val vehicleOptions = VehicleOptions()

        val drivingSession = drivingRouter.requestRoutes(
            requestPoints,
            drivingOptions,
            vehicleOptions,
            object : DrivingSession.DrivingRouteListener {
                override fun onDrivingRoutes(routes: List<DrivingRoute>) {
                    if (routes.isNotEmpty()) {
                        mapObjects.addPolyline(routes[0].geometry).apply {
                            setStrokeColor(ContextCompat.getColor(context, R.color.purple_700))
                            strokeWidth = 5f
                        }

                        val points = routes[0].geometry.points
                        if (points.isNotEmpty()) {
                            var minLat = points[0].latitude
                            var maxLat = points[0].latitude
                            var minLon = points[0].longitude
                            var maxLon = points[0].longitude

                            for (point in points) {
                                minLat = minOf(minLat, point.latitude)
                                maxLat = maxOf(maxLat, point.latitude)
                                minLon = minOf(minLon, point.longitude)
                                maxLon = maxOf(maxLon, point.longitude)
                            }

                            val center = Point(
                                (minLat + maxLat) / 2,
                                (minLon + maxLon) / 2
                            )

                            mapView.map.move(
                                CameraPosition(center, 10f, 0f, 0f),
                                Animation(Animation.Type.SMOOTH, 1f),
                                null
                            )
                        }
                    } else {
                        mapLoadError = true
                    }
                }

                override fun onDrivingRoutesError(error: Error) {
                    Log.e("MapView", "Ошибка построения маршрута: $error")
                    Toast.makeText(context, "Ошибка построения маршрута", Toast.LENGTH_SHORT).show()
                    mapLoadError = true
                }
            }
        )

        onDispose {
            drivingSession.cancel()
            mapView.onStop()
        }
    }

    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { mapView }
        )

        if (mapLoadError) {
            Text(
                text = "Не удалось загрузить карту. Проверьте подключение к интернету.",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                color = androidx.compose.ui.graphics.Color.Red
            )
        }
    }
}