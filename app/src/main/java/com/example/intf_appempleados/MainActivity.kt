package com.example.intf_appempleados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.intf_appempleados.navigation.NavGraph

// MainActivity es la actividad principal de la aplicación que se ejecuta al inicio
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita el modo edge-to-edge (pantalla completa sin márgenes)
        setContent {
            Intf_AppEmpleados2() // Establece la función composable que define la UI
        }
    }
}

// Composable principal de la aplicación
@Composable
fun Intf_AppEmpleados2() {
    // Controlador de navegación que maneja las rutas de las pantallas
    val navController = rememberNavController()

    // Usa el tema de la aplicación definido en AppTheme (Theme.kt)
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), // Hace que la superficie ocupe todo el tamaño disponible
            color = MaterialTheme.colorScheme.background // Define el color de fondo de la superficie
        ) {
            // Llama a la función NavGraph que define las pantallas y navegación
            NavGraph(navController)
        }
    }
}
