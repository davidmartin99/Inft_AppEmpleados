package com.example.intf_appempleados.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {
    // Conversión de temperatura
    var temperatureCelsius by mutableStateOf("")
    var temperatureFahrenheit by mutableStateOf("")

    // Hora en distintas ciudades
    var currentCityTime: String by mutableStateOf("")
    var citiesTime: Map<String, String> by mutableStateOf(emptyMap())


    // Teléfonos de contacto
    var contacts: Map<String, String> by mutableStateOf(emptyMap())

    // Funciones para conversión de temperatura
    fun convertCelsiusToFahrenheit(celsius: String) {
        val celsiusValue = celsius.toDoubleOrNull()
        if (celsiusValue != null) {
            temperatureFahrenheit = (celsiusValue * 9 / 5 + 32).toString()
        }
    }

    fun convertFahrenheitToCelsius(fahrenheit: String) {
        val fahrenheitValue = fahrenheit.toDoubleOrNull()
        if (fahrenheitValue != null) {
            temperatureCelsius = ((fahrenheitValue - 32) * 5 / 9).toString()
        }
    }


}
