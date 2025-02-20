package com.example.intf_appempleados.ui.screens

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.example.intf_appempleados.ui.viewmodel.TimeViewModel
import java.util.Calendar
import androidx.lifecycle.viewmodel.compose.viewModel

// Lista de ciudades con su nombre y zona horaria
val ciudades = listOf(
    "Madrid" to "Europe/Madrid",
    "París" to "Europe/Paris",
    "Londres" to "Europe/London",
    "Porto Alegre" to "America/Sao_Paulo",
    "Acapulco" to "America/Mexico_City",
    "Vancouver" to "America/Vancouver",
    "Houston" to "America/Chicago",
    "Casablanca" to "Africa/Casablanca",
    "Osaka, Japón" to "Asia/Tokyo",
    "Melbourne, Australia" to "Australia/Melbourne",
    "Ankara, Turquía" to "Europe/Istanbul",
    "Dubai, Emiratos Árabes Unidos" to "Asia/Dubai"
)

// Mapa que asocia cada ciudad con su imagen
val imagenesPaises = mapOf(
    "Madrid" to R.drawable.mapa_espana,
    "París" to R.drawable.mapa_francia,
    "Londres" to R.drawable.mapa_reino_unido,
    "Porto Alegre" to R.drawable.mapa_brasil,
    "Acapulco" to R.drawable.mapa_mexico,
    "Vancouver" to R.drawable.mapa_canada,
    "Houston" to R.drawable.mapa_eeuu,
    "Casablanca" to R.drawable.mapa_marruecos,
    "Osaka, Japón" to R.drawable.mapa_japon,
    "Melbourne, Australia" to R.drawable.mapa_australia,
    "Ankara, Turquía" to R.drawable.mapa_turquia,
    "Dubai, Emiratos Árabes Unidos" to R.drawable.mapa_emiratos
)

// Composable principal que contiene la interfaz de la pantalla de hora
@Composable
fun TimeScreen(viewModel: TimeViewModel = viewModel()) {
    // Estados para la ciudad seleccionada y la hora de referencia
    val ciudadSeleccionada by viewModel.ciudadSeleccionada.collectAsState()
    val horaReferencia by viewModel.horaReferencia.collectAsState()

    // Formateador de hora
    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    // Obtener el contexto local
    val context = LocalContext.current

    // Column para organizar todos los elementos en la pantalla
    Column(modifier = Modifier.fillMaxSize().padding(4.dp)) {
        // Selector de ciudades (LazyRow para un scroll horizontal)
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(ciudades) { ciudad ->
                // Botón para seleccionar una ciudad
                OutlinedButton(
                    onClick = { viewModel.seleccionarCiudad(ciudad) },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (ciudad == ciudadSeleccionada) MaterialTheme.colorScheme.surfaceContainerHighest else Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Text(ciudad.first, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Espacio entre elementos
        Spacer(modifier = Modifier.height(20.dp))

        // Mostrar el mapa y la hora de la ciudad seleccionada
        val imagenCiudad = imagenesPaises[ciudadSeleccionada.first] ?: R.drawable.global
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Imagen del mapa de la ciudad
            Image(
                painter = painterResource(id = imagenCiudad),
                contentDescription = "Mapa de ${ciudadSeleccionada.first}",
                modifier = Modifier.size(135.dp)
            )
            Spacer(modifier = Modifier.width(18.dp))
            // Información sobre la ciudad seleccionada y la hora
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Nombre de la ciudad
                Text(
                    text = ciudadSeleccionada.first,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                // Hora de referencia con un borde
                Text(
                    text = horaReferencia.format(formatter),
                    fontSize = 40.sp,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .border(2.dp, color = MaterialTheme.colorScheme.tertiary, shape = MaterialTheme.shapes.medium) // Borde
                        .padding(3.dp) // Espaciado dentro del borde
                        .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Botón para seleccionar una nueva hora
                OutlinedButton(
                    onClick = { showTimePicker(context) { viewModel.seleccionarHora(it) } },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onTertiaryContainer),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Icono de reloj
                        Icon(
                            painter = painterResource(id = R.drawable.schedule),
                            contentDescription = "Seleccionar Hora",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // Texto del botón
                        Text("Selecciona hora", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Lista de otras ciudades con sus horas
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
        ) {
            items(ciudades.filter { it != ciudadSeleccionada }) { (nombre, zona) ->
                // Calcular la hora local para cada ciudad
                val horaLocal = horaReferencia.withZoneSameInstant(ZoneId.of(zona))

                // Tarjeta con la información de la ciudad
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Imagen de la ciudad
                        Image(
                            painter = painterResource(id = imagenesPaises[nombre] ?: R.drawable.global),
                            contentDescription = "Mapa de $nombre",
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        // Información sobre la ciudad y su hora
                        Column(modifier = Modifier.weight(1f)) {
                            // Nombre de la ciudad
                            Text(
                                text = nombre,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            // Hora local de la ciudad
                            Text(
                                text = horaLocal.format(formatter),
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier
                                    .border(2.dp, color = MaterialTheme.colorScheme.tertiary, shape = MaterialTheme.shapes.medium) // Borde
                                    .padding(3.dp) // Espaciado dentro del borde
                                    .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
                            )
                        }
                    }
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

    // Mostrar el TimePickerDialog
    TimePickerDialog(
        context,
        { _, selectedHour, selectedMinute ->
            onTimeSelected(LocalTime.of(selectedHour, selectedMinute)) // Actualizar la hora seleccionada
        },
        hour,
        minute,
        true
    ).show()
}

@Preview(showBackground = true)
@Composable
fun PreviewZonaHorariaScreen() {
    TimeScreen() // Vista previa de la pantalla
}
