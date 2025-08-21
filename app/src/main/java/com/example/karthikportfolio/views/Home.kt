package com.example.karthikportfolio.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun Home() {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Text("home", fontSize = 28.sp)
    }
}