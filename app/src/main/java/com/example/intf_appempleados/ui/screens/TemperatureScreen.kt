package com.example.intf_appempleados.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.intf_appempleados.R
import com.example.intf_appempleados.ui.viewmodel.TemperatureViewModel

@Composable
fun TemperatureScreen(viewModel: TemperatureViewModel = viewModel()) {
    // Obtener el valor de la temperatura, si se está usando Celsius y el historial de temperaturas desde el ViewModel
    val temperatura by viewModel.temperature.collectAsState()
    val esCelsius by viewModel.esCelsius.collectAsState()
    val historial by viewModel.historial.collectAsState()

    // Convertir la temperatura si es necesario
    val temperaturaConvertida = if (esCelsius) (temperatura * 9 / 5 + 32).toInt()
    else ((temperatura - 32) * 5 / 9).toInt()

    // Definir la imagen en función de la temperatura
    val imagenTemperatura = when {
        temperatura.toInt() <= 12 -> R.drawable.cold_temperature_logo
        temperatura.toInt() in 13..25 -> R.drawable.heat_temperature_logo
        else -> R.drawable.hot_temperature_logo
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp), // Configura el layout de la columna
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos
    ) {
        // Contenedor de imagen con el logo de temperatura
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Muestra la imagen relacionada con la temperatura
            Image(
                painter = painterResource(id = imagenTemperatura),
                contentDescription = "Imagen de temperatura",
                modifier = Modifier.size(280.dp)
            )

            // Muestra el texto de la temperatura en Celsius y Fahrenheit, encima de la imagen
            Text(
                text = "${temperatura.toInt()} ${if (esCelsius) "°C" else "°F"}   " +
                        "$temperaturaConvertida ${if (esCelsius) "°F" else "°C"}",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold, // Aplica negrita al texto
                    shadow = Shadow(
                        color = MaterialTheme.colorScheme.onTertiaryContainer, // Color de la sombra
                        offset = Offset(9f, 7f), // Posición de la sombra
                        blurRadius = 7f // Difuminado de la sombra
                    )
                ),
                modifier = Modifier.align(Alignment.Center), // Alinea el texto en el centro
            )
        }

        Spacer(modifier = Modifier.height(15.dp)) // Espacio entre la imagen y los botones

        // Selección de unidades Celsius o Fahrenheit con botones
        Row(
            horizontalArrangement = Arrangement.Center, // Centra los botones
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Botón para seleccionar Celsius
            Button(
                onClick = { if (!esCelsius) viewModel.cambiarUnidad() }, // Cambia la unidad
                colors = ButtonDefaults.buttonColors(containerColor = if (esCelsius) MaterialTheme.colorScheme.primary else Color.LightGray)
            ) {
                Text("Celsius", color = Color.White) // Texto en el botón
            }
            Spacer(modifier = Modifier.width(16.dp)) // Espacio entre los botones
            // Botón para seleccionar Fahrenheit
            Button(
                onClick = { if (esCelsius) viewModel.cambiarUnidad() }, // Cambia la unidad
                colors = ButtonDefaults.buttonColors(containerColor = if (!esCelsius) MaterialTheme.colorScheme.primary else Color.LightGray)
            ) {
                Text("Fahrenheit", color = Color.White) // Texto en el botón
            }
        }

        Spacer(modifier = Modifier.height(13.dp)) // Espacio entre los botones y el slider

        // Slider para ajustar la temperatura
        Slider(
            value = temperatura,
            onValueChange = { viewModel.cambiarTemperatura(it) }, // Cambia la temperatura
            valueRange = if (esCelsius) -30f..55f else -22f..131f, // Rango de valores según la unidad
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary, // Color del pulgar
                activeTrackColor = MaterialTheme.colorScheme.primaryContainer, // Color de la pista activa
                inactiveTrackColor = Color.LightGray // Color de la pista inactiva
            ),
            modifier = Modifier
                .fillMaxWidth(0.85f) // Hace el slider más ancho
                .height(24.dp) // Ajusta la altura del slider
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre el slider y el botón de guardar

        // Botón de guardar
        Button(
            onClick = { viewModel.guardarTemperatura() }, // Guarda la temperatura
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier
                .fillMaxWidth(0.4f) // Hace el botón más pequeño
                .height(38.dp) // Ajusta la altura del botón
        ) {
            Text(
                "Guardar",
                fontSize = 14.sp, // Tamaño de la fuente en el botón
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold // Aplica negrita al texto
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre el botón de guardar y el historial

        // Historial de temperaturas con iconos y texto
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(), // Ocupa todo el espacio disponible
            verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente
            horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
        ) {
            // Itera sobre el historial de temperaturas
            items(historial) { temp ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                        .background(color = MaterialTheme.colorScheme.onSecondary),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // Determina el icono en función de la temperatura
                    val icono = when {
                        temp.first <= 12 -> R.drawable.cold_temperature_logo
                        temp.first in 13..25 -> R.drawable.heat_temperature_logo
                        else -> R.drawable.hot_temperature_logo
                    }

                    // Muestra el icono de la temperatura
                    Image(
                        painter = painterResource(id = icono),
                        contentDescription = "Icono temperatura",
                        modifier = Modifier.size(30.dp) // Hace el icono más pequeño
                    )

                    Spacer(modifier = Modifier.width(5.dp)) // Espacio entre el icono y el texto

                    // Muestra la temperatura en Celsius y Fahrenheit
                    Text(
                        text = "${temp.first}°C   ${temp.second}°F",
                        fontSize = 18.sp, // Tamaño de la fuente más pequeño
                        color = Color.Black,
                        fontWeight = FontWeight.Bold, // Aplica negrita al texto
                        style = MaterialTheme.typography.bodyLarge // Estilo de texto
                    )
                }
            }
        }
    }
}

// Vista previa de la pantalla de temperatura
@Preview(showBackground = true)
@Composable
fun PreviewTemperaturaScreen() {
    TemperatureScreen() // Muestra la pantalla de temperatura en la vista previa
}
