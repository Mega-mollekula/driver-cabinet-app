package com.example.drivercabinet.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.drivercabinet.presentation.viewmodel.AuthState
import com.example.drivercabinet.presentation.viewmodel.AuthViewModel

@Composable
fun ConfirmScreen(
    viewModel: AuthViewModel,
    email: String,
    onSuccess: () -> Unit
) {
    var code by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Подтверждение для $email", style = MaterialTheme.typography.titleMedium)

        TextField(value = code, onValueChange = { code = it }, label = { Text("Код подтверждения") })

        Button(
            onClick = {
                viewModel.confirm(email, code) {
                    onSuccess()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Подтвердить")
        }

        when (val currentState = state) {
            is AuthState.Loading -> CircularProgressIndicator()
            is AuthState.Success -> Text(currentState.message, color = Color.Green)
            is AuthState.Error -> Text(currentState.message, color = Color.Red)
            else -> {}
        }
    }
}
