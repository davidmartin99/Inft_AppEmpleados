package com.example.intf_appempleados.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.intf_appempleados.ui.screens.DataScreen
import com.example.intf_appempleados.ui.screens.TemperatureScreen
import com.example.intf_appempleados.ui.screens.TimeScreen
import com.example.intf_appempleados.ui.viewmodel.AppViewModel

@Composable
fun SetupNavGraph(navController: NavController, viewModel: AppViewModel, paddingValues: PaddingValues) {
    NavHost(navController = navController as NavHostController, startDestination = "temperature") {
        composable("temperature") {
            TemperatureScreen() // Navegar a la pantalla de temperatura
        }
        composable("time") {
            TimeScreen() // Navegar a la pantalla de hora
        }
        composable("data") {
            DataScreen() // Navegar a la pantalla de datos
        }
    }
}

