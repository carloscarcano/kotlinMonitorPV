package com.cc.monitor

import com.cc.monitor.models.SensorModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class SensorProvider {
    fun obtenerListaSensores(): List<SensorModel> {
        val lista = listOf(
            SensorModel(
                100,
                "Sensor 1",
                "Ubicación Sensor 1",
                Random.nextInt(0, 2),
                10,
                "01/01/2022",
                "Marca A",
                "Modelo A"
            ),
            SensorModel(
                200,
                "Sensor 2",
                "Ubicación Sensor 2",
                Random.nextInt(0, 2),
                20,
                "02/01/2022",
                "Marca B",
                "Modelo B"
            ),
            SensorModel(
                300,
                "Sensor 3",
                "Ubicación Sensor 3",
                Random.nextInt(0, 2),
                30,
                "03/01/2022",
                "Marca C",
                "Modelo C"
            ),
            SensorModel(
                400,
                "Sensor 4",
                "Ubicación Sensor 4",
                Random.nextInt(0, 2),
                40,
                "05/01/2022",
                "Marca D",
                "Modelo D"
            )
        )

        return lista
    }
}
