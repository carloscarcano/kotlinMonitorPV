package com.cc.monitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.cc.monitor.databinding.ActivityLoginBinding
import com.cc.monitor.models.LoginRequestModel
import com.cc.monitor.models.LoginResultModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener { iniciar() }

        binding.btnEntrar.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.etUsuario.setText("")
        binding.etPassword.setText("")
        binding.etUsuario.requestFocus()
        binding.btnEntrar.isEnabled = true
        binding.btnEntrar.isClickable = true
    }

    private fun iniciar() {
        val etUsuario: String = binding.etUsuario.text.toString().trim()
        val etPassword: String = binding.etPassword.text.toString().trim()

        Snackbar.make(binding.root,  "Espere mientras se validan los datos...", Snackbar.LENGTH_SHORT).show()

        //continuarLogin(LoginResultModel(true, "Carlos Carcaño", "Yucatán"), "abc")

        if (etUsuario.isNotEmpty() && etPassword.isNotEmpty())
        {
            binding.btnEntrar.isEnabled = false
            binding.btnEntrar.isClickable = false

            CoroutineScope(Dispatchers.IO).launch {
                val result = RestEngine.getRestEngine().create(RestServicios::class.java)
                            .validarLogin(LoginRequestModel(etUsuario, etPassword)
                )
                val resultBody = result.body()

                runOnUiThread {
                    binding.btnEntrar.isEnabled = true
                    binding.btnEntrar.isClickable = true

                    if (result.isSuccessful) {
                        continuarLogin(resultBody!!, etUsuario)
                    } else {
                        msotrarErrorLogin()
                    }
                }
            }
        } else {
            Toast.makeText(this, "Escriba el usuario y contraseña", Toast.LENGTH_SHORT).show()
        }
    }

    private fun continuarLogin(resultModelBody: LoginResultModel, usuario: String) {
        if (resultModelBody.login) {
            val activityPrincipal = Intent(this, PrincipalActivity::class.java)

            activityPrincipal.putExtra("APP_USUARIO", usuario)
            activityPrincipal.putExtra("APP_NOMBRE", resultModelBody.nombre)
            activityPrincipal.putExtra("APP_ESTADO", resultModelBody.estado)
            startActivity(activityPrincipal)
        } else {
            Toast.makeText(this, "El usuario y/o contraseña son incorrectos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun msotrarErrorLogin() {
        Toast.makeText(this, "Ocurrió un error al intentar realizar el login", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}