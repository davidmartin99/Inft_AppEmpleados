package com.example.intf_appempleados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.intf_appempleados.ui.viewmodel.AppViewModel
import com.example.intf_appempleados.navigation.SetupNavGraph


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel = AppViewModel())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: AppViewModel) {
    val navHostController = rememberNavController()

    // Estado para controlar la pantalla seleccionada
    var selectedTab by remember { mutableStateOf("temperature") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Empresa XYZ") },
                actions = {
                    IconButton(onClick = { /* Acción de ajustes */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Ajustes")
                    }
                    IconButton(onClick = { /* Acción de login */ }) {
                        Icon(Icons.Default.Person, contentDescription = "Login")
                    }
                }
            )
        },
        bottomBar = {
            // Barra inferior personalizada con íconos
            Row {
                // Ícono de Temperatura
                IconButton(onClick = {
                    selectedTab = "temperature"
                    navHostController.navigate("temperature")  // Navega a la pantalla de temperatura
                }) {
                    Icon(Icons.Default.Home, contentDescription = "Temperatura")
                }

                // Ícono de Hora
                IconButton(onClick = {
                    selectedTab = "time"
                    navHostController.navigate("time")  // Navega a la pantalla de hora
                }) {
                    Icon(Icons.Default.Home, contentDescription = "Hora")
                }

                // Ícono de Datos
                IconButton(onClick = {
                    selectedTab = "data"
                    navHostController.navigate("data")  // Navega a la pantalla de datos
                }) {
                    Icon(Icons.Default.Home, contentDescription = "Datos")
                }
            }
        }
    ) { paddingValues ->
        // Aquí se maneja la navegación, pasa paddingValues si es necesario
        SetupNavGraph(navController = navHostController, viewModel = viewModel, paddingValues = paddingValues)
    }
}


@Composable
fun TemperatureScreen(viewModel: AppViewModel) {
    Text(text = "Pantalla de Conversión de Temperatura")
}

@Composable
fun TimeScreen(viewModel: AppViewModel, city: String) {
    Text(text = "Pantalla de Hora en las ciudades - Ciudad actual: $city")
}

@Composable
fun DataScreen(viewModel: AppViewModel) {
    Text(text = "Pantalla de Contactos")
}


// Preview de MainScreen
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(viewModel = AppViewModel())
}