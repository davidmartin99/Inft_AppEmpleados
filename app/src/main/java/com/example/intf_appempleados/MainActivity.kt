package com.example.intf_appempleados


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
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
import com.example.intf_appempleados.ui.viewmodel.AppViewModel
import androidx.compose.material3.BottomNavigation
import androidx.compose.material3.BottomNavigationItem



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivity(viewModel: AppViewModel) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ayuda Empleados Multinacional") },
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
            BottomNavigation(
                selectedItemIndex = selectedTab,
                onItemSelected = { selectedTab = it }
            ) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.TemperatureHigh, contentDescription = "Conversión de Temperatura") },
                    label = { Text("Temperatura") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.AccessTime, contentDescription = "Horas") },
                    label = { Text("Horas") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Phone, contentDescription = "Contactos") },
                    label = { Text("Contactos") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
            }
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> TemperatureConversionScreen(viewModel)
            1 -> CitiesTimeScreen(viewModel, "Madrid") // Aquí se puede pasar la ciudad actual
            2 -> ContactsScreen(viewModel)
        }
    }
}

@Composable
fun TemperatureConversionScreen(viewModel: AppViewModel) {
    // Tu código para la conversión de temperatura
    Text(text = "Pantalla de Conversión de Temperatura")
}

@Composable
fun CitiesTimeScreen(viewModel: AppViewModel, city: String) {
    // Tu código para mostrar la hora en las diferentes ciudades
    Text(text = "Pantalla de Hora en las ciudades - Ciudad actual: $city")
}

@Composable
fun ContactsScreen(viewModel: AppViewModel) {
    // Tu código para mostrar los contactos
    Text(text = "Pantalla de Contactos")
}


