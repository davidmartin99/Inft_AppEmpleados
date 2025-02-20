package com.example.empleadosmultinacional.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.intf_appempleados.R
import com.example.intf_appempleados.data.ciudadesContacto
import com.example.intf_appempleados.data.contactos
import com.example.intf_appempleados.data.imagenesPaises
import com.example.intf_appempleados.data.servicios
import com.example.intf_appempleados.ui.viewmodel.DataViewModel


@Composable
fun DataScreen(viewModel: DataViewModel = viewModel()) {
    // Obtener los estados del ViewModel
    val ciudadSeleccionada by viewModel.ciudadSeleccionada.collectAsState()
    val servicioSeleccionado by viewModel.servicioSeleccionado.collectAsState()

    // Obtener la información de contacto para la ciudad y servicio seleccionados
    val contactoInfo = contactos[ciudadSeleccionada]?.get(servicioSeleccionado)
    // Obtener la imagen del país de la ciudad seleccionada
    val imagenPais = imagenesPaises[ciudadSeleccionada] ?: R.drawable.global

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Selector de Ciudad
        SelectorDropdown(
            label = "Ciudad",
            opciones = ciudadesContacto,
            seleccion = ciudadSeleccionada,
            onSeleccionChange = { viewModel.seleccionarCiudad(it) } // Actualiza el estado de la ciudad
        )

        Spacer(modifier = Modifier.height(3.dp))

        // Selector de Servicio debajo de Ciudad
        SelectorDropdown(
            label = "Servicio",
            opciones = listOf("Emergencias") + servicios.filter { it != "Emergencias" },
            seleccion = servicioSeleccionado,
            onSeleccionChange = { viewModel.seleccionarServicio(it) } // Actualiza el estado del servicio
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Mostrar imagen del mapa del país seleccionado
        Image(
            painter = painterResource(id = imagenPais),
            contentDescription = "Mapa de $ciudadSeleccionada",
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Mostrar la ciudad y el servicio seleccionado dentro de una tarjeta
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary) // Borde de la tarjeta
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "$ciudadSeleccionada - $servicioSeleccionado", // Texto de la ciudad y el servicio
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold, // Texto en negrita
                    color = MaterialTheme.colorScheme.primary // Color del texto
                )
                // Mostrar teléfono
                Text(
                    text = contactoInfo?.telefono ?: "Teléfono no disponible",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary // Color del texto
                )
            }
        }

        // Si existe un contacto, mostrar los detalles de la persona de contacto
        contactoInfo?.nombreContacto?.let { nombre ->
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Contacto: $nombre", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Teléfono: ${contactoInfo.telefonoContacto}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Email: ${contactoInfo.emailContacto}", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

// Composable para el Selector de Ciudad y Servicio
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorDropdown(
    label: String, // Etiqueta del selector
    opciones: List<String>, // Lista de opciones para seleccionar
    seleccion: String, // Valor seleccionado
    onSeleccionChange: (String) -> Unit // Función para actualizar el valor seleccionado
) {
    var expanded by remember { mutableStateOf(false) } // Estado para saber si el menú está expandido

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // Componente de menú desplegable
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it } // Controlar la expansión del menú
        ) {
            // Campo de texto donde se muestra la opción seleccionada
            OutlinedTextField(
                value = seleccion,
                onValueChange = {}, // No cambia el valor, solo se usa para mostrarlo
                readOnly = true, // El campo es solo de lectura
                label = {
                    Text(
                        label,
                        fontSize = 18.sp, // Tamaño del texto
                        fontWeight = FontWeight.Bold, // Negrita en la etiqueta
                        color = MaterialTheme.colorScheme.primary // Color de la etiqueta
                    )
                },
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 18.sp, // Tamaño del texto dentro del campo
                    fontWeight = FontWeight.Bold, // Negrita dentro del campo
                    color = MaterialTheme.colorScheme.secondary // Color del texto dentro del campo
                ),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor() // Posicionar el menú correctamente
                    .fillMaxWidth(0.8f) // Ajustar el ancho del campo de texto
            )
            // Menú desplegable con las opciones
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false } // Cerrar el menú cuando se haga clic fuera
            ) {
                opciones.forEach { opcion -> // Iterar sobre las opciones
                    DropdownMenuItem(
                        text = {
                            Text(
                                opcion,
                                fontSize = 15.sp, // Tamaño de texto dentro del menú
                                fontWeight = FontWeight.Bold, // Negrita dentro del menú
                                color = MaterialTheme.colorScheme.secondary  // Color del texto dentro del menú
                            )
                        },
                        onClick = {
                            onSeleccionChange(opcion) // Actualizar el valor seleccionado
                            expanded = false // Cerrar el menú
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContactosScreen() {
    DataScreen() // Vista previa del composable DataScreen
}
