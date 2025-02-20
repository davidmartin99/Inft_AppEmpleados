package com.example.intf_appempleados.ui.viewmodel

import androidx.lifecycle.ViewModel
import java.time.LocalTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.ZoneId
import java.time.ZonedDateTime

class TimeViewModel : ViewModel() {
    // Estado que almacena la ciudad seleccionada y su zona horaria en un flujo mutable
    private val _ciudadSeleccionada = MutableStateFlow("Madrid" to "Europe/Madrid")
    val ciudadSeleccionada: StateFlow<Pair<String, String>> = _ciudadSeleccionada

    // Estado que almacena la hora seleccionada en un flujo mutable
    private val _horaSeleccionada = MutableStateFlow(LocalTime.now())
    val horaSeleccionada: StateFlow<LocalTime> = _horaSeleccionada

    // Estado que almacena la hora de referencia calculada, basada en la ciudad seleccionada y la hora seleccionada
    private val _horaReferencia = MutableStateFlow(obtenerHoraReferencia())
    val horaReferencia: StateFlow<ZonedDateTime> = _horaReferencia

    // Método para cambiar la ciudad seleccionada y actualizar la hora de referencia
    fun seleccionarCiudad(ciudad: Pair<String, String>) {
        _ciudadSeleccionada.value = ciudad
        actualizarHoraReferencia() // 🔹 Actualiza la hora de referencia tras cambiar la ciudad
    }

    // Método para cambiar la hora seleccionada y actualizar la hora de referencia
    fun seleccionarHora(hora: LocalTime) {
        _horaSeleccionada.value = hora
        actualizarHoraReferencia() // 🔹 Actualiza la hora de referencia tras cambiar la hora
    }

    // Método privado para actualizar la hora de referencia
    private fun actualizarHoraReferencia() {
        _horaReferencia.value = obtenerHoraReferencia()
    }

    // Método que obtiene la hora de referencia calculada según la ciudad y la hora seleccionada
    fun obtenerHoraReferencia(): ZonedDateTime {
        val zonaHoraria = ZoneId.of(_ciudadSeleccionada.value.second) // Obtiene la zona horaria de la ciudad
        return ZonedDateTime.of(
            _horaSeleccionada.value.atDate(ZonedDateTime.now(zonaHoraria).toLocalDate()), // Combina la hora seleccionada con la fecha actual de la zona horaria
            zonaHoraria
        )
    }
}
