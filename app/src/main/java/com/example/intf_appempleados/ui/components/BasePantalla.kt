package com.example.intf_appempleados.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intf_appempleados.R

// Composable para la pantalla base con una barra superior y una barra inferior de navegación
@Composable
fun BasePantalla(
    title: String, // Título de la pantalla
    onNavigateToTemperature: () -> Unit, // Función para navegar a la pantalla de temperatura
    onNavigateToTimeZone: () -> Unit, // Función para navegar a la pantalla de zona horaria
    onNavigateToContacts: () -> Unit, // Función para navegar a la pantalla de contactos
    content: @Composable (Modifier) -> Unit // Contenido de la pantalla que se pasa como parámetro
) {
    Scaffold(
        topBar = { TopBar(title) }, // Barra superior con el título
        bottomBar = {
            // Barra de navegación inferior con los botones para navegar entre las pantallas
            BottomNavigationBar(
                onNavigateToTemperature,
                onNavigateToTimeZone,
                onNavigateToContacts
            )
        }
    ) { innerPadding ->
        // Caja que ocupa todo el tamaño disponible y aplica un padding
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Transparent)
        ) {
            content(Modifier.padding(innerPadding)) // Aquí se muestra el contenido de la pantalla
        }
    }
}

// Barra superior con el título y los botones de acción (ajustes y login)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        title = {
            // Título centrado en la barra superior
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primaryContainer,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            // Logo de la empresa que aparece en la barra superior
            Image(
                painter = painterResource(id = R.drawable.logo_splatnot),
                contentDescription = "Logo de la empresa",
                modifier = Modifier.size(120.dp)
            )
        },
        actions = {
            // Icono de ajustes en la barra superior
            IconButton(onClick = { /* Acción de ajustes */ }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.inversePrimary)
            }
            // Icono de login en la barra superior
            IconButton(onClick = { /* Acción de login */ }) {
                Icon(Icons.Default.Person, contentDescription = "Login", tint = MaterialTheme.colorScheme.inversePrimary)
            }
        }
    )
}

// Barra de navegación inferior con iconos para cambiar entre pantallas
@Composable
fun BottomNavigationBar(
    onNavigateToTemperature: () -> Unit, // Función para navegar a la pantalla de temperatura
    onNavigateToTimeZone: () -> Unit, // Función para navegar a la pantalla de zona horaria
    onNavigateToContacts: () -> Unit // Función para navegar a la pantalla de contactos
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
        // Item de navegación para la pantalla de temperatura
        NavigationBarItem(
            selected = false,
            onClick = onNavigateToTemperature,
            icon = { Icon(painterResource(id = R.drawable.thermostat), tint = MaterialTheme.colorScheme.onSecondary, contentDescription = "Temperatura") },
            label = { Text(text = "Temperatura", style = TextStyle(color = MaterialTheme.colorScheme.onTertiary)) }
        )
        // Item de navegación para la pantalla de zona horaria
        NavigationBarItem(
            selected = false,
            onClick = onNavigateToTimeZone,
            icon = { Icon(painterResource(id = R.drawable.schedule), tint = MaterialTheme.colorScheme.onSecondary, contentDescription = "Horarios") },
            label = { Text(text = "Horarios", style = TextStyle(color = MaterialTheme.colorScheme.onTertiary)) }
        )
        // Item de navegación para la pantalla de contactos
        NavigationBarItem(
            selected = false,
            onClick = onNavigateToContacts,
            icon = { Icon(painterResource(id = R.drawable.contacts), tint = MaterialTheme.colorScheme.onSecondary, contentDescription = "Contactos") },
            label = { Text(text = "Contactos",  style = TextStyle(color = MaterialTheme.colorScheme.onTertiary) ) }
        )
    }
}

// Vista previa de la BasePantalla con un ejemplo de uso
@Preview(showBackground = true)
@Composable
fun BasePantallaPreview() {
    BasePantalla(
        title = "Pantalla de Ejemplo",
        onNavigateToTemperature = {},
        onNavigateToTimeZone = {},
        onNavigateToContacts = {}
    ) { modifier ->
        // Se muestra un fondo gris claro con texto de ejemplo en el centro
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            Text(text = "Contenido de la pantalla", color = Color.Black)
        }
    }
}
