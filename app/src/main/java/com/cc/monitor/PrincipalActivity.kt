package com.cc.monitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cc.monitor.adapter.SensorAdapter
import com.cc.monitor.databinding.ActivityPrincipalBinding
import com.cc.monitor.models.SensorModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PrincipalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)

        Toast.makeText(
            this,
            "Recuperando lista de sensores...",
            Toast.LENGTH_SHORT
        ).show()

        CoroutineScope(Dispatchers.IO).launch {
            val result =
                RestEngine.getRestEngine().create(RestServicios::class.java).obtenerSensores()
            val resultBody = result.body()

            runOnUiThread {
                if (result.isSuccessful) {
                    with(binding.recyclerView) {
                        addItemDecoration(decoration)
                        layoutManager = manager
                        adapter =
                            SensorAdapter(resultBody!!) { sensorItem ->
                                recyclerViewItemClick(sensorItem)
                            }
                    }

                } else {
                    mostrarErrorActualizarRecyclerView()
                }
            }
        }
    }

    private fun actualizarRecyclerView() {
        initRecyclerView()
    }

    private fun recyclerViewItemClick(sensor: SensorModel) {
        val activityDetalleSensor = Intent(this, DetalleSensorActivity::class.java)

        with(activityDetalleSensor) {
            putExtra("SENSOR_NOMBRE", sensor.nombre)
            putExtra("SENSOR_UBICACION", sensor.ubicacion)
            putExtra("SENSOR_ESTATUS", sensor.estatus)
            putExtra("SENSOR_MARCA", sensor.marca)
            putExtra("SENSOR_MODELO", sensor.modelo)
            putExtra("SENSOR_REGISTRO", sensor.fechaRegistro)
            putExtra("SENSOR_APERTURAS", sensor.aperturas)
            startActivity(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemActualizar -> {
                actualizarRecyclerView()
            }
            R.id.itemPerfil -> {
                val bundle = intent.extras
                val usrUsuario = bundle?.get("APP_USUARIO").toString()
                val usrNombre = bundle?.get("APP_NOMBRE").toString()
                val usrEstado = bundle?.get("APP_ESTADO").toString()

                val ventana = AlertDialog.Builder(this)
                ventana.setTitle("Usuario Logeado")
                ventana.setMessage("Usuario: $usrUsuario" + "\n" + "Nombre: $usrNombre" + "\n" + "Estado: $usrEstado")
                ventana.setNegativeButton("OK", null)
                ventana.create()
                ventana.show()
            }
            R.id.itemCerrarSesion -> {
                finishAndRemoveTask()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun mostrarErrorActualizarRecyclerView() {
        Toast.makeText(
            this,
            "Se produjo un error al intentar recuperar la lista",
            Toast.LENGTH_SHORT
        ).show()
    }
}