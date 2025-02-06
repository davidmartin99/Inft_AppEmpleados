package com.example.intf_appempleados.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intf_appempleados.R
import com.example.intf_appempleados.ui.viewmodel.AppViewModel


@Composable
fun TemperatureScreen(viewModel: AppViewModel, paddingValues: PaddingValues) {
    // Variables para manejar la temperatura y el tipo de escala
    var temperature by remember { mutableStateOf(0f) }
    var isCelsius by remember { mutableStateOf(true) }
    var savedTemperatures = remember { mutableStateListOf<String>() }

    // Función para convertir de Celsius a Fahrenheit
    fun celsiusToFahrenheit(celsius: Float) = (celsius * 9 / 5) + 32

    // Función para guardar la temperatura y mostrar el icono correspondiente
    fun saveTemperature() {
        val tempCelsius = if (isCelsius) temperature else (temperature - 32) * 5 / 9
        val tempFahrenheit = celsiusToFahrenheit(tempCelsius)
        val tempString = "${tempCelsius.toInt()} ºC - ${tempFahrenheit.toInt()} ºF"

        if (savedTemperatures.size == 50) {
            savedTemperatures.removeAt(0)  // Eliminar el primer registro si hay más de 50
        }

        savedTemperatures.add(tempString)
    }

    // Función que asigna el icono basado en la temperatura
    val getTemperatureIcon = {
        when {
            temperature <= 12 -> R.drawable.cold_temperature_logo // Icono frío
            temperature <= 25 -> R.drawable.heat_temperature_logo // Icono templado
            else -> R.drawable.hot_temperature_logo // Icono calor
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), // Ocupa pantalala según tamaño del topBar y bottomBar
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen que cambia según la temperatura
        Image(
            painter = painterResource(id = getTemperatureIcon()), // Usar la función para obtener el icono
            contentDescription = "Imagen de Temperatura",
            modifier = Modifier.size(250.dp)
        )

        // Slider para ajustar la temperatura
        Slider(
            value = temperature,
            onValueChange = { temperature = it },
            valueRange = -30f..55f,
            steps = 85,
            modifier = Modifier.fillMaxWidth(),
            onValueChangeFinished = { saveTemperature() }
        )

        // Mostrar la temperatura seleccionada en Celsius o Fahrenheit
        Text(
            text = "Temperatura: ${temperature.toInt()} ${if (isCelsius) "ºC" else "ºF"}",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Opciones para cambiar entre Celsius y Fahrenheit
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Celsius")
            RadioButton(
                selected = isCelsius,
                onClick = { isCelsius = true }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text("Fahrenheit")
            RadioButton(
                selected = !isCelsius,
                onClick = { isCelsius = false }
            )
        }

        // Botón para guardar la temperatura
        Button(
            onClick = { saveTemperature() },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(text = "Guardar Temperatura")
        }

        // Mostrar las últimas temperaturas guardadas
        Column(modifier = Modifier.fillMaxHeight()) {
            Text(
                text = "Últimas Temperaturas",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            savedTemperatures.forEach { temp ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = getTemperatureIcon()), // Usar el mismo icono
                        contentDescription = "Icono Temperatura",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = temp,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
