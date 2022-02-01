package com.cc.monitor.adapter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cc.monitor.models.SensorModel
import com.cc.monitor.databinding.ItemSensorBinding

class SensorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemSensorBinding.bind(view)

    fun render(sensor: SensorModel, onClickListener:(SensorModel) -> Unit) {
        binding.tvNombre.text = sensor.nombre
        binding.tvUbicacion.text = sensor.ubicacion
        if (sensor.estatus == 1) {
            binding.tvEstatus.text = "Abierto"
            binding.tvEstatus.setTextColor(Color.parseColor("#ff0000"))
        } else {
            binding.tvEstatus.text = "Cerrado"
            binding.tvEstatus.setTextColor(Color.parseColor("#000000"))
        }

        itemView.setOnClickListener { onClickListener(sensor) }
    }
}