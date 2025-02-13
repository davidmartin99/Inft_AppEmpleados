package com.example.intf_appempleados.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.empleadosmultinacional.ui.screens.DataScreen
import com.example.intf_appempleados.ui.screens.TemperatureScreen
import com.example.intf_appempleados.ui.screens.TimeScreen
import com.example.intf_appempleados.ui.viewmodel.AppViewModel


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: AppViewModel,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "temperature" // Pantalla de arranque
    ) {
        composable("temperature") {
            TemperatureScreen(viewModel = viewModel, paddingValues = paddingValues)
        }

        composable("time") {
            TimeScreen(viewModel = viewModel, paddingValues = paddingValues)

        }

        composable("data") {
            DataScreen(viewModel = viewModel, paddingValues = paddingValues)

        }

    }
}

