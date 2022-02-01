package com.cc.monitor.models

data class SensorModel(
    var id: Int,
    var nombre: String,
    var ubicacion: String,
    var estatus: Int,
    var aperturas: Int,
    var fechaRegistro: String,
    var marca: String,
    var modelo: String
)
