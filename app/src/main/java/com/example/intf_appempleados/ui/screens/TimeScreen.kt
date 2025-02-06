package com.example.intf_appempleados.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.intf_appempleados.ui.viewmodel.AppViewModel


@Composable
fun TimeScreen(viewModel: AppViewModel, paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green) // Fondo verde claro
    ) {
        Text(
            text = "Pantalla de Hora",
            color = Color.White, // Color de texto blanco
            modifier = Modifier.align(Alignment.Center) // Centrar el texto
        )
    }
}
