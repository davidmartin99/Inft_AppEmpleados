package com.example.empleadosmultinacional.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.intf_appempleados.R
import com.example.intf_appempleados.data.ciudadesContacto
import com.example.intf_appempleados.data.contactos
import com.example.intf_appempleados.data.imagenesPaises
import com.example.intf_appempleados.data.servicios
import com.example.intf_appempleados.ui.viewmodel.AppViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataScreen(viewModel: AppViewModel, paddingValues: PaddingValues) {
    var ciudadSeleccionada by remember { mutableStateOf(ciudadesContacto.firstOrNull() ?: "") }
    var servicioSeleccionado by remember { mutableStateOf("Emergencias") }

    val contactoInfo = contactos[ciudadSeleccionada]?.get(servicioSeleccionado)
    val imagenPais = imagenesPaises[ciudadSeleccionada] ?: R.drawable.global

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), // Ocupa pantalla según tamaño del topBar y bottomBar
    ) {

        // Selector de Ciudad
        SelectorDropdown(
            label = "Ciudad",
            opciones = ciudadesContacto,
            seleccion = ciudadSeleccionada,
            onSeleccionChange = { ciudadSeleccionada = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Selector de Servicio debajo de Ciudad
        SelectorDropdown(
            label = "Servicio",
            opciones = listOf("Emergencias") + servicios.filter { it != "Emergencias" },
            seleccion = servicioSeleccionado,
            onSeleccionChange = { servicioSeleccionado = it }
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Imagen del mapa del país seleccionado
        Image(
            painter = painterResource(id = imagenPais),
            contentDescription = "Mapa de $ciudadSeleccionada",
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Mostrar la ciudad y el servicio seleccionado
        Text(
            text = "$ciudadSeleccionada - $servicioSeleccionado",
            style = MaterialTheme.typography.titleLarge
        )

        // Teléfono en tamaño grande
        Text(
            text = contactoInfo?.telefono ?: "Teléfono no disponible",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary
        )

        // Si el servicio tiene una persona de contacto, mostrar los detalles
        contactoInfo?.nombreContacto?.let { nombre ->
            Spacer(modifier = Modifier.height(8.dp))
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


// Composable para los Selectores de Ciudad y Servicio
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorDropdown(
    label: String,
    opciones: List<String>,
    seleccion: String,
    onSeleccionChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = seleccion,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        onSeleccionChange(opcion)
                        expanded = false
                    }
                )
            }
        }
    }
}

