package com.example.intf_appempleados.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
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
    var savedTemperatures = remember { mutableStateListOf<Pair<String, Int>>() } // Guardamos la temperatura y el icono asociado
    var savedIcon by remember { mutableStateOf<Int>(R.drawable.cold_temperature_logo) } // Guardar icono guardado

    // Función para convertir de Celsius a Fahrenheit
    fun celsiusToFahrenheit(celsius: Float) = (celsius * 9 / 5) + 32

    // Función para convertir de Fahrenheit a Celsius
    fun fahrenheitToCelsius(fahrenheit: Float) = (fahrenheit - 32) * 5 / 9

    // Función para obtener el icono correspondiente según la temperatura
    fun getTemperatureIcon(tempCelsius: Float): Int {
        return when {
            tempCelsius <= 12 -> R.drawable.cold_temperature_logo // Icono frío
            tempCelsius <= 25 -> R.drawable.heat_temperature_logo // Icono templado
            else -> R.drawable.hot_temperature_logo // Icono calor
        }
    }

    // Función para guardar la temperatura y el icono
    fun saveTemperature() {
        // Convertir la temperatura a Celsius si está en Fahrenheit
        val tempCelsius = if (isCelsius) temperature else fahrenheitToCelsius(temperature)

        // Convertir Celsius a Fahrenheit para mostrar ambos valores
        val tempFahrenheit = celsiusToFahrenheit(tempCelsius)

        // Obtener el icono correspondiente
        val iconRes = getTemperatureIcon(tempCelsius)

        // Limitar el número de temperaturas guardadas a 50
        if (savedTemperatures.size == 50) {
            savedTemperatures.removeAt(0)  // Eliminar el primer registro si hay más de 50
        }

        // Guardar la temperatura y el icono
        savedTemperatures.add(Pair("${tempCelsius.toInt()} ºC / ${tempFahrenheit.toInt()} ºF", iconRes))

        // Guardar el icono actual
        savedIcon = iconRes
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues) // Ocupa pantalla según tamaño del topBar y bottomBar
            .padding(horizontal = 16.dp), // Agrega padding a los lados
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar la temperatura seleccionada en Celsius o Fahrenheit arriba del icono
        Box(
            contentAlignment = Alignment.Center, // Centra el contenido dentro del Box
            modifier = Modifier.size(250.dp)
        ) {
            // Imagen de temperatura
            Image(
                painter = painterResource(id = getTemperatureIcon(if (isCelsius) temperature else fahrenheitToCelsius(temperature))),
                contentDescription = "Imagen de Temperatura",
                modifier = Modifier.size(250.dp)
            )

            // Texto superpuesto sobre la imagen
            Text(
                text = "${temperature.toInt()}ºC / ${celsiusToFahrenheit(temperature).toInt()}ºF",
                fontSize = 40.sp,
                style = MaterialTheme.typography.titleLarge // Aplicar fuentes del MaterialTheme
            )

        }


        // Slider para ajustar la temperatura
        Slider(
            value = if (isCelsius) temperature else fahrenheitToCelsius(temperature),
            onValueChange = { temp ->
                // Cambiar la temperatura en la escala seleccionada
                temperature = if (isCelsius) temp else celsiusToFahrenheit(temp)
            },
            valueRange = -30f..55f,
            steps = 85,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(horizontal = 4.dp) // Añadir padding en los lados
        )

        // Opciones para cambiar entre Celsius y Fahrenheit
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Celsius", style = MaterialTheme.typography.bodyMedium)
            RadioButton(
                selected = isCelsius,
                onClick = { isCelsius = true }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text("Fahrenheit", style = MaterialTheme.typography.bodyMedium)
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
            Text(text = "Guardar Temperatura", style = MaterialTheme.typography.titleLarge)
        }

        // Mostrar las últimas temperaturas guardadas
        Column(modifier = Modifier.fillMaxHeight()) {
            Text(
                text = "Últimas Temperaturas",
                fontSize = 18.sp,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            savedTemperatures.forEach { (temp, iconRes) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = iconRes), // Usar el icono guardado
                        contentDescription = "Icono Temperatura",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = temp,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
