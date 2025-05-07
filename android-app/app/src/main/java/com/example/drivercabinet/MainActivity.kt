package com.example.drivercabinet

import AppNavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.drivercabinet.common.di.AppModule
import com.yandex.mapkit.MapKitFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey("0736cdea-203b-491b-804c-4cdb9f643864")

        MapKitFactory.initialize(this)
        super.onCreate(savedInstanceState)
        setContent {
            AppNavGraph(viewModel = AppModule.authViewModel)
        }
    }
}
