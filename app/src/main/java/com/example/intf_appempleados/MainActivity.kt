package com.example.intf_appempleados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.compose.primaryLight
import com.example.compose.secondaryLight
import com.example.intf_appempleados.navigation.SetupNavGraph
import com.example.intf_appempleados.ui.viewmodel.AppViewModel

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
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Coloca tu imagen en la carpeta res/drawable
                            contentDescription = "Logo"

                        )

                        Text(
                            text = "Splanot",
                            style = MaterialTheme.typography.titleLarge,  // Usa la tipografía definida
                            color = Color.White,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Acción de ajustes */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                    IconButton(onClick = { /* Acción de login */ }) {
                        Icon(Icons.Default.Person, contentDescription = "Login")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryLight  // Uso los colores de theme
                )

            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = secondaryLight, // Uso los colores de theme
                contentColor = Color.White,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Espaciado horizontal
                    horizontalArrangement = Arrangement.SpaceBetween, // Espaciado entre íconos
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    // Ícono de Temperatura
                    // Temperature Icon
                    IconButton(onClick = {
                        selectedTab = "temperature"
                        navHostController.navigate("temperature")  // Navigate to temperature screen
                    }) {
                        Icon(Icons.Default.Home, contentDescription = "Temperature")
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
        }
    ) { paddingValues ->
        // Aquí se maneja la navegación, pasa paddingValues si es necesario
        SetupNavGraph(navController = navHostController, viewModel = viewModel, paddingValues = paddingValues)
    }// Fin Scafold
}// Fin MainScreen



// Preview de MainScreen
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(viewModel = AppViewModel())
}