package com.tecsup.edu.restaurantetrujillo

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PlacesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placestourist)

        supportActionBar?.hide()

        val textView = findViewById<TextView>(R.id.txtChanChan)
        textView.setOnClickListener {
            val intent = Intent(this, ChanChanMapActivity::class.java)
            startActivity(intent)
        }
        val textViewSir = findViewById<TextView>(R.id.txtSirSipan)
        textView.setOnClickListener {
            val intent = Intent(this, ChanChanMapActivity::class.java)
            startActivity(intent)
        }


    }
}