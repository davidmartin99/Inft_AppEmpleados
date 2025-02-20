package com.example.intf_appempleados.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TemperatureViewModel : ViewModel() {
    // Estado que almacena la temperatura actual en un flujo mutable (por defecto, 0f)
    private val _temperatura = MutableStateFlow(0f)
    val temperature: StateFlow<Float> = _temperatura

    // Estado que indica si la unidad actual es Celsius (true) o Fahrenheit (false)
    private val _esCelsius = MutableStateFlow(true)
    val esCelsius: StateFlow<Boolean> = _esCelsius

    // Historial de temperaturas almacenadas, con un máximo de 50 registros
    private val _historial = MutableStateFlow<List<Pair<Int, Int>>>(emptyList())
    val historial: StateFlow<List<Pair<Int, Int>>> = _historial

    // Método para cambiar la temperatura actual
    fun cambiarTemperatura(valor: Float) {
        _temperatura.value = valor
    }

    // Método para cambiar la unidad de temperatura entre Celsius y Fahrenheit
    fun cambiarUnidad() {
        _esCelsius.value = !_esCelsius.value
    }

    // Método para guardar la temperatura actual en el historial con su conversión
    fun guardarTemperatura() {
        // Convierte la temperatura actual a la otra unidad
        val tempConvertida = if (_esCelsius.value)
            (_temperatura.value * 9 / 5 + 32).toInt() // Celsius a Fahrenheit
        else
            ((_temperatura.value - 32) * 5 / 9).toInt() // Fahrenheit a Celsius

        // Crea una nueva lista basada en el historial actual
        val nuevaLista = _historial.value.toMutableList()

        // Si el historial ya tiene 50 elementos, elimina el más antiguo
        if (nuevaLista.size == 50) nuevaLista.removeAt(0)

        // Agrega la nueva temperatura al historial
        nuevaLista.add(Pair(_temperatura.value.toInt(), tempConvertida))
        _historial.value = nuevaLista
    }
}
