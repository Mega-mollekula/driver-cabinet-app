package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.Driver
import com.example.myapplication.ui.theme.Beige
import com.example.myapplication.ui.theme.LightBrown
import com.example.myapplication.ui.theme.Orange

@Composable
fun HomeScreen(
    driver: Driver = Driver(
        fullName = "Иванов Иван Иванович",
        status = "Онлайн",
        email = "ivanov@example.com",
        phone = "+7 123 456-78-90"
    ),
    onEditClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Beige
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .border(2.dp, Orange, CircleShape)
                    .clip(CircleShape)
                    .background(LightBrown),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Avatar",
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(driver.fullName, style = MaterialTheme.typography.headlineSmall)
            Text(driver.status, color = Orange, style = MaterialTheme.typography.labelLarge)

            Spacer(Modifier.height(24.dp))

            ProfileField("Имя", driver.fullName)
            ProfileField("Статус водителя", driver.status)
            ProfileField("Почта", driver.email)
            ProfileField("Телефон", driver.phone)

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = onEditClick,
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Редактировать профиль")
            }
        }
    }
}

@Composable
fun ProfileField(label: String, value: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .background(Color.White, RoundedCornerShape(8.dp))
        .border(1.dp, Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
        .padding(12.dp)
    ) {
        Text(label, color = Color.Gray, style = MaterialTheme.typography.labelSmall)
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
}