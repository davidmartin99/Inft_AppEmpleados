package com.example.intf_appempleados.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Clase ViewModel que gestionará los datos para la UI.
class DataViewModel : ViewModel() {

    // MutableStateFlow para almacenar el valor de la ciudad seleccionada.
    // Inicialmente, el valor es "Madrid, España".
    private val _ciudadSeleccionada = MutableStateFlow("Madrid, España")

    // StateFlow solo de lectura que expone el valor de la ciudad seleccionada.
    val ciudadSeleccionada: StateFlow<String> = _ciudadSeleccionada

    // MutableStateFlow para almacenar el valor del servicio seleccionado.
    // Inicialmente, el valor es "Emergencias".
    private val _servicioSeleccionado = MutableStateFlow("Emergencias")

    // StateFlow solo de lectura que expone el valor del servicio seleccionado.
    val servicioSeleccionado: StateFlow<String> = _servicioSeleccionado

    // Función para actualizar el valor de la ciudad seleccionada.
    // Recibe como parámetro el nombre de la ciudad y actualiza el valor.
    fun seleccionarCiudad(ciudad: String) {
        _ciudadSeleccionada.value = ciudad
    }

    // Función para actualizar el valor del servicio seleccionado.
    // Recibe como parámetro el nombre del servicio y actualiza el valor.
    fun seleccionarServicio(servicio: String) {
        _servicioSeleccionado.value = servicio
    }
}
