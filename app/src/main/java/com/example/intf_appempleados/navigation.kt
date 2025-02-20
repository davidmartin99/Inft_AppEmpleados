package com.example.intf_appempleados.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.intf_appempleados.ui.screens.DataScreen
import com.example.intf_appempleados.ui.components.BasePantalla
import com.example.intf_appempleados.ui.screens.TemperatureScreen
import com.example.intf_appempleados.ui.screens.TimeScreen
import com.example.intf_appempleados.ui.viewmodel.TemperatureViewModel
import com.example.intf_appempleados.ui.viewmodel.TimeViewModel
import com.example.intf_appempleados.ui.viewmodel.DataViewModel

// Clase sellada que define las pantallas y sus rutas en la navegación
sealed class Screen(val route: String) {
    object Temperature : Screen("temperature_screen") // Ruta para la pantalla de temperatura
    object Time : Screen("time_screen") // Ruta para la pantalla de zona horaria
    object Data : Screen("data_screen") // Ruta para la pantalla de contactos
}

// Composable que define la navegación entre pantallas
@Composable
fun NavGraph(navController: NavHostController) {
    // Crear los ViewModels a nivel del NavGraph para ser compartidos entre pantallas
    val temperatureViewModel: TemperatureViewModel = viewModel() // ViewModel para la pantalla de temperatura
    val zonaHorariaViewModel: TimeViewModel = viewModel() // ViewModel para la pantalla de zona horaria
    val contactosViewModel: DataViewModel = viewModel() // ViewModel para la pantalla de contactos

    // Define el NavHost que maneja la navegación entre las pantallas
    NavHost(
        navController = navController,
        startDestination = Screen.Temperature.route // Pantalla inicial (Temperature)
    ) {
        // Pantalla de temperatura
        composable(Screen.Temperature.route) {
            // BasePantalla con un título y acciones de navegación
            BasePantalla(
                title = "Temperature",
                onNavigateToTemperature = { navController.navigate(Screen.Temperature.route) },
                onNavigateToTimeZone = { navController.navigate(Screen.Time.route) },
                onNavigateToContacts = { navController.navigate(Screen.Data.route) }
            ) { modifier ->
                // Pasar el ViewModel de temperatura a la pantalla
                TemperatureScreen(viewModel = temperatureViewModel)
            }
        }

        // Pantalla de zona horaria
        composable(Screen.Time.route) {
            // BasePantalla con un título y acciones de navegación
            BasePantalla(
                title = "Time Zone",
                onNavigateToTemperature = { navController.navigate(Screen.Temperature.route) },
                onNavigateToTimeZone = { navController.navigate(Screen.Time.route) },
                onNavigateToContacts = { navController.navigate(Screen.Data.route) }
            ) { modifier ->
                // Pasar el ViewModel de zona horaria a la pantalla
                TimeScreen(viewModel = zonaHorariaViewModel)
            }
        }

        // Pantalla de contactos
        composable(Screen.Data.route) {
            // BasePantalla con un título y acciones de navegación
            BasePantalla(
                title = "Contacts",
                onNavigateToTemperature = { navController.navigate(Screen.Temperature.route) },
                onNavigateToTimeZone = { navController.navigate(Screen.Time.route) },
                onNavigateToContacts = { navController.navigate(Screen.Data.route) }
            ) { modifier ->
                // Pasar el ViewModel de contactos a la pantalla
                DataScreen(viewModel = contactosViewModel)
            }
        }
    }
}
