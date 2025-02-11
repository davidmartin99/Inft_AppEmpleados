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

    // Funciones para manejo de horas
    fun updateCityTimes(currentCity: String) {
        // Aquí agregas lógica para obtener las horas de las distintas ciudades
        val timeMap = mapOf(
            "Madrid" to "12:00",
            "París" to "12:00",
            "Londres" to "12:00",
            "Nueva York" to "06:00",
            "Tokio" to "20:00",
            "Los Ángeles" to "05:00",
            "Sydney" to "23:00",
            "Berlín" to "13:00",
            "Roma" to "13:00",
            "Moscú" to "15:00",
            "Seúl" to "20:00",
            "Beijing" to "19:00",
            "Buenos Aires" to "07:00",
            "México" to "05:00",
            "Toronto" to "06:00"
        )
        citiesTime = timeMap
        currentCityTime = timeMap[currentCity] ?: "00:00"
    }

    // Funciones para manejo de contactos
    fun updateContacts() {
        val contactMap = mapOf(
            "Madrid" to "Emergencias: 112, Taxi: 123456789",
            "París" to "Emergencias: 112, Taxi: 987654321",
            "Londres" to "Emergencias: 999, Taxi: 0207946575",
            "Nueva York" to "Emergencias: 911, Taxi: 2125551234",
            "Tokio" to "Emergencias: 110, Taxi: 03-3505-2121",
            "Los Ángeles" to "Emergencias: 911, Taxi: 3235551234",
            "Sydney" to "Emergencias: 000, Taxi: 131008",
            "Berlín" to "Emergencias: 112, Taxi: 030/202020",
            "Roma" to "Emergencias: 112, Taxi: 060609",
            "Moscú" to "Emergencias: 112, Taxi: 4957777777",
            "Seúl" to "Emergencias: 112, Taxi: 02-1234-5678",
            "Beijing" to "Emergencias: 110, Taxi: 010-67532312",
            "Buenos Aires" to "Emergencias: 911, Taxi: 011-47777777",
            "México" to "Emergencias: 911, Taxi: 5555555555",
            "Toronto" to "Emergencias: 911, Taxi: 4165551234"
        )
        contacts = contactMap
    }
}
