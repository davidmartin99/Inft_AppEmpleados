package com.example.intf_appempleados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.intf_appempleados.navigation.SetupNavGraph
import com.example.intf_appempleados.ui.viewmodel.AppViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aplica el tema global a toda la app usando el tema personalizado
            AppTheme(
                darkTheme = isSystemInDarkTheme(),  // Determina si el sistema usa el modo oscuro o claro
                dynamicColor = true  // Activa colores dinámicos si el sistema lo soporta
            ) {
                // Componente principal de la app
                MainScreen(viewModel = AppViewModel())
            }
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
                            painter = painterResource(id = R.drawable.logo_splatnot), // Coloca tu imagen en la carpeta res/drawable
                            contentDescription = "Logo",
                            modifier = Modifier.size(150.dp) // Ajusta el tamaño del logo aquí (40.dp es un ejemplo)

                        )

                        // Texto aquí
                    }
                },
                actions = {
                    IconButton(onClick = { /* Acción de ajustes */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                    IconButton(onClick = { /* Acción de login */ }) {
                        Icon(Icons.Default.Person, contentDescription = "Login", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary // Uso el color primario del theme
                )
            )
        }, //Fin TopBar

        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer, // Uso el color secundario del theme
                contentColor = MaterialTheme.colorScheme.onSecondary,
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
                    IconButton(
                        onClick = {
                            selectedTab = "temperature"
                            navHostController.navigate("temperature")  // Navegar a la pantalla de temperatura
                        },
                        modifier = Modifier.size(100.dp) // Tamaño del botón cuadrado, 100.dp de tamaño
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, // Centra los elementos horizontalmente
                            verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente
                            modifier = Modifier.fillMaxSize() // Asegura que la columna llene todo el espacio del botón
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.thermostat),
                                contentDescription = "Temperature",
                                tint = MaterialTheme.colorScheme.onSecondary, // Color del ícono según el tema
                                modifier = Modifier.size(48.dp) // Tamaño del ícono, 48.dp para que no sea tan grande
                            )
                            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre el ícono y el texto
                            Text(
                                text = "Temperatura",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal), // Estilo del texto según el tema
                                color = MaterialTheme.colorScheme.onSecondary, // Color del texto según el tema
                                textAlign = TextAlign.Center, // Asegura que el texto esté centrado
                                modifier = Modifier.fillMaxWidth() // Asegura que el texto ocupe todo el ancho disponible
                            )
                        }
                    }//Fin Temperature

                    // Ícono de Hora
                    IconButton(
                        onClick = {
                            selectedTab = "time"
                            navHostController.navigate("time")  // Navegar a la pantalla de hora
                        },
                        modifier = Modifier.size(100.dp) // Tamaño del botón cuadrado
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, // Centra los elementos horizontalmente
                            verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente
                            modifier = Modifier.fillMaxSize() // Asegura que la columna llene todo el espacio del botón
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.schedule),
                                contentDescription = "Hora",
                                tint = MaterialTheme.colorScheme.onSecondary, // Color del ícono según el tema
                                modifier = Modifier.size(48.dp) // Tamaño del ícono
                            )
                            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre el ícono y el texto
                            Text(
                                text = "Hora",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal), // Estilo del texto según el tema
                                color = MaterialTheme.colorScheme.onSecondary, // Color del texto según el tema
                                textAlign = TextAlign.Center, // Asegura que el texto esté centrado
                                modifier = Modifier.fillMaxWidth() // Asegura que el texto ocupe todo el ancho disponible
                            )
                        }
                    }//Fin Hora

                    // Ícono de Datos
                    IconButton(
                        onClick = {
                            selectedTab = "data"
                            navHostController.navigate("data")  // Navegar a la pantalla de datos
                        },
                        modifier = Modifier.size(100.dp) // Tamaño del botón cuadrado
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, // Centra los elementos horizontalmente
                            verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente
                            modifier = Modifier.fillMaxSize() // Asegura que la columna llene todo el espacio del botón
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.contacts),
                                contentDescription = "Datos",
                                tint = MaterialTheme.colorScheme.onSecondary, // Color del ícono según el tema
                                modifier = Modifier.size(48.dp) // Tamaño del ícono
                            )
                            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre el ícono y el texto
                            Text(
                                text = "Datos",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal), // Estilo del texto según el tema
                                color = MaterialTheme.colorScheme.onSecondary, // Color del texto según el tema
                                textAlign = TextAlign.Center, // Asegura que el texto esté centrado
                                modifier = Modifier.fillMaxWidth() // Asegura que el texto ocupe todo el ancho disponible
                            )
                        }
                    }//Fin Datos


                }//Fin Row
            }
        }
    ) { paddingValues ->
        // Aquí se maneja la navegación, pasa paddingValues si es necesario
        SetupNavGraph(navController = navHostController, viewModel = viewModel, paddingValues = paddingValues)
    }
}

// Preview de MainScreen
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(viewModel = AppViewModel())
}
