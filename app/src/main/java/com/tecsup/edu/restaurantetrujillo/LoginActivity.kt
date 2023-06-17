package com.tecsup.edu.restaurantetrujillo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val buttonLogin = findViewById<Button>(R.id.btnIngresar)
        buttonLogin.setOnClickListener {
            startActivity(Intent(this, PlatosActivity::class.java))
        }

        supportActionBar?.hide()


    }


}