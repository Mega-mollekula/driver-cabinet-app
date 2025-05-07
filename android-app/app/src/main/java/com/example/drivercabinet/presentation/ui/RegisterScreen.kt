package com.example.drivercabinet.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
fun RegisterScreen(
    viewModel: AuthViewModel,
    onNavigateToConfirm: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    fun validateFields(): Boolean {
        return when {
            name.isEmpty() -> {
                errorMessage = "Имя не может быть пустым"
                false
            }
            email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                errorMessage = "Введите корректный email"
                false
            }
            phone.isEmpty() -> {
                errorMessage = "Телефон не может быть пустым"
                false
            }
            password.isEmpty() || password.length < 6 -> {
                errorMessage = "Пароль должен быть не менее 6 символов"
                false
            }
            else -> true
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Имя") })
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = phone, onValueChange = { phone = it }, label = { Text("Телефон") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Пароль") })

        // Покажем ошибку, если есть
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }

        Button(
            onClick = {
                if (validateFields()) {
                    viewModel.register(name, email, phone, password) {
                        onNavigateToConfirm(email)
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Зарегистрироваться")
        }

        when (val currentState = state) {
            is AuthState.Loading -> CircularProgressIndicator()
            is AuthState.Success -> Text(currentState.message, color = Color.Green)
            is AuthState.Error -> Text(currentState.message, color = Color.Red)
            else -> {}
        }
    }
}
