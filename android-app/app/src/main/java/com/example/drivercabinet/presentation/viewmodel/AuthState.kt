package com.example.drivercabinet.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drivercabinet.data.model.ApproveRequest
import com.example.drivercabinet.data.model.LoginRequest
import com.example.drivercabinet.data.model.RegisterRequest
import com.example.drivercabinet.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> = _state

    fun register(name: String, email: String, phone: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            try {
                val request = RegisterRequest(name, email, phone, password)
                Log.d("Register", "Отправка запроса на регистрацию: $request")
                val result = authRepository.register(request)
                Log.d("Register", "Ответ от репозитория: $result")
                _state.value = AuthState.Success("Регистрация прошла успешно")
                onSuccess()
            } catch (e: Exception) {
                Log.e("Register", "Ошибка регистрации", e)
                _state.value = AuthState.Error("Ошибка регистрации: ${e.localizedMessage}")
            }
        }
    }

    fun confirm(email: String, code: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            try {
                val request = ApproveRequest(email, code)
                Log.d("Confirm", "Отправка запроса на подтверждение: $request")
                val result = authRepository.confirm(request)
                Log.d("Confirm", "Ответ от репозитория: $result")
                _state.value = AuthState.Success("Регистрация прошла успешно")
                _state.value = AuthState.Success("Email подтвержден")
                onSuccess()
            } catch (e: Exception) {
                Log.e("Confirm", "Ошибка подтверждения", e)
                _state.value = AuthState.Error("Ошибка подтверждения: ${e.localizedMessage}")
            }
        }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            try {
                val request = LoginRequest(email, password)
                Log.d("Login", "Отправка запроса на вход: $request")
                val result = authRepository.login(request)
                Log.d("Login", "Ответ от репозитория: $result")
                _state.value = AuthState.Success("Вход выполнен успешно")
                onSuccess()
            } catch (e: Exception) {
                Log.e("Login", "Ошибка входа", e)
                _state.value = AuthState.Error("Ошибка входа: ${e.localizedMessage}")
            }
        }
    }
}
