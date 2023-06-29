package com.tecsup.edu.restaurantetrujillo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var mFirestore: FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        mFirestore = FirebaseFirestore.getInstance()

        // Configurar los listeners de los botones de inicio de sesión y registro

        // Configurar los listeners de los botones de inicio de sesión y registro
        val loginButton = findViewById<Button>(R.id.btnIngresar)

        val textView = findViewById<TextView>(R.id.registerView)
        textView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        loginButton.setOnClickListener { // Lógica de inicio de sesión
            login()
        }
    }


    private fun login() {
        val emailEditText = findViewById<TextInputEditText>(R.id.emailEditText)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordEditText)
        val email = emailEditText.text.toString().trim { it <= ' ' }
        val password = passwordEditText.text.toString().trim { it <= ' ' }
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // El inicio de sesión fue exitoso
                    Toast.makeText(
                        this@LoginActivity,
                        "Inicio de sesión exitoso",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                    startActivity(Intent(this, PlatosActivity::class.java))

                    // Continúa con la lógica de tu aplicación después del inicio de sesión
                } else {
                    // Error en el inicio de sesión
                    Toast.makeText(
                        this@LoginActivity,
                        "Error en el inicio de sesión",
                        Toast.LENGTH_SHORT
                    )
                        .show()


                }
            }
    }


}