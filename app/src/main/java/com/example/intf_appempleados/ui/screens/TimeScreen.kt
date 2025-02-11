package com.example.intf_appempleados.ui.screens

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intf_appempleados.R
import com.example.intf_appempleados.ui.viewmodel.AppViewModel
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

// Definir las ciudades con sus zonas horarias
val ciudades = listOf(
    "Madrid" to "Europe/Madrid",
    "París" to "Europe/Paris",
    "Londres" to "Europe/London",
    "Porto Alegre" to "America/Sao_Paulo",
    "Acapulco" to "America/Mexico_City",
    "Vancouver" to "America/Vancouver",
    "Houston" to "America/Chicago",
    "Casablanca" to "Africa/Casablanca",
    "Osaka" to "Asia/Tokyo",
    "Melbourne" to "Australia/Melbourne",
    "Ankara" to "Europe/Istanbul",
    "Dubai" to "Asia/Dubai"
)

// Mapa de imágenes por ciudad
val imagenesPaises = mapOf(
    "Madrid" to R.drawable.mapa_espana,
    "París" to R.drawable.mapa_francia,
    "Londres" to R.drawable.mapa_reino_unido,
    "Porto Alegre" to R.drawable.mapa_brasil,
    "Acapulco" to R.drawable.mapa_mexico,
    "Vancouver" to R.drawable.mapa_canada,
    "Houston" to R.drawable.mapa_eeuu,
    "Casablanca" to R.drawable.mapa_marruecos,
    "Osaka" to R.drawable.mapa_japon,
    "Melbourne" to R.drawable.mapa_australia,
    "Ankara" to R.drawable.mapa_turquia,
    "Dubai" to R.drawable.mapa_emiratos
)

@Composable
fun TimeScreen(viewModel: AppViewModel, paddingValues: PaddingValues) {
    var ciudadSeleccionada by remember { mutableStateOf(ciudades.first()) }
    var horaSeleccionada by remember { mutableStateOf(LocalTime.now()) }
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
                start = 8.dp,
                end = 8.dp
            )
    ) {
        // Selector horizontal de ciudades con LazyRow
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(ciudades) { ciudad ->
                Button(
                    onClick = { ciudadSeleccionada = ciudad },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (ciudad == ciudadSeleccionada) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(ciudad.first, color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Obtener la hora en la ciudad seleccionada
        val zonaHoraria = ZoneId.of(ciudadSeleccionada.second)
        val horaReferencia = ZonedDateTime.of(
            horaSeleccionada.atDate(ZonedDateTime.now(zonaHoraria).toLocalDate()),
            zonaHoraria
        )

        // Mostrar el mapa, el nombre de la ciudad y la hora en la ciudad seleccionada
        val imagenCiudad = imagenesPaises[ciudadSeleccionada.first] ?: R.drawable.global

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Espacio entre los elementos
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Mapa de la ciudad
            Image(
                painter = painterResource(id = imagenCiudad),
                contentDescription = "Mapa de ${ciudadSeleccionada.first}",
                modifier = Modifier
                    .size(120.dp) // Tamaño fijo para los mapas
                    .weight(1f) // Esto hace que el mapa ocupe una parte del espacio
            )

            // Contenedor para la hora
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Botón para elegir la hora
                Button(
                    onClick = {
                        showTimePicker(context) { selectedTime ->
                            horaSeleccionada = selectedTime
                        }
                    }
                ) {
                    Text("Elegir hora")
                }

                // Hora en la ciudad seleccionada
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = horaReferencia.format(formatter),
                    fontSize = 40.sp, // Tamaño grande para la hora
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        // Nombre de la ciudad debajo de la imagen
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = ciudadSeleccionada.first,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Oficinas")
        LazyColumn {
            items(ciudades.filter { it != ciudadSeleccionada }) { (nombre, zona) ->
                val horaLocal = horaReferencia.withZoneSameInstant(ZoneId.of(zona))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Distribuye los elementos uniformemente
                ) {
                    Image(
                        painter = painterResource(id = imagenesPaises[nombre] ?: R.drawable.global),
                        contentDescription = "Mapa de $nombre",
                        modifier = Modifier.size(60.dp) // Imagen más pequeña
                    )

                    Text(
                        text = nombre,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.weight(1f) // Hace que el texto ocupe espacio uniforme
                    )

                    Text(
                        text = horaLocal.format(formatter),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            }
        }


    }
}

// Función para mostrar el selector de hora
fun showTimePicker(context: Context, onTimeSelected: (LocalTime) -> Unit) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    TimePickerDialog(
        context,
        { _, selectedHour, selectedMinute ->
            onTimeSelected(LocalTime.of(selectedHour, selectedMinute))
        },
        hour,
        minute,
        true
    ).show()
}

