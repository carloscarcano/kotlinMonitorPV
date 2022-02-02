package com.cc.monitor

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.cc.monitor.databinding.ActivityDetalleSensorBinding

class DetalleSensorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleSensorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleSensorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mostrarDatos()
    }

    private fun mostrarDatos() {
        val datosSensor = intent.extras

        with(binding) {
            tvNombre.text = datosSensor?.getString("SENSOR_NOMBRE")
            tvUbicacion.text = datosSensor?.getString("SENSOR_UBICACION")
            if (datosSensor?.getInt("SENSOR_ESTATUS") == 1) {
                tvEstatus.text = "Abierto"
                binding.tvEstatus.setTextColor(Color.parseColor("#ff0000"))
            } else {
                tvEstatus.text = "Cerrado"
                binding.tvEstatus.setTextColor(Color.parseColor("#ff018786"))
            }
            tvMarca.text = datosSensor?.getString("SENSOR_MARCA")
            tvModelo.text = datosSensor?.getString("SENSOR_MODELO")
            tvFechaRegistro.text = datosSensor?.getString("SENSOR_REGISTRO")
            tvAperturas.text =datosSensor?.getInt("SENSOR_APERTURAS").toString()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}