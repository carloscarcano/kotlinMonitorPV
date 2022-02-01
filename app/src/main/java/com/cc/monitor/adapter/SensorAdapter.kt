package com.cc.monitor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cc.monitor.R
import com.cc.monitor.models.SensorModel

class SensorAdapter(private val sensorList: List<SensorModel>, private val onClickListener:(SensorModel) -> Unit) : RecyclerView.Adapter<SensorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sensor, parent, false)

        return SensorViewHolder(view)
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        val sensorItem = sensorList[position]
        holder.render(sensorItem, onClickListener)
    }

    override fun getItemCount(): Int = sensorList.size
}