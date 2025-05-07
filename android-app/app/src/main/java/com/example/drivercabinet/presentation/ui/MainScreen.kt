package com.example.drivercabinet.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.navigation.NavHostController
import com.example.myapplication.screens.HomeScreen

@Composable
fun MainScreen(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(0) }
    val screens = listOf("Home", "Orders")

    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        icon = {
                            when (screen) {
                                "Home" -> Icon(Icons.Default.Person, contentDescription = screen)
                                "Orders" -> Icon(Icons.Default.List, contentDescription = screen)
                            }
                        },
                        label = { Text(screen) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize()
        ) {
            when (selectedItem) {
                0 -> HomeScreen(
                    onEditClick = {
                        navController.navigate("edit_profile")
                    }
                )
                1 -> {
                    OrdersScreen()
                }
            }
        }
    }
}
