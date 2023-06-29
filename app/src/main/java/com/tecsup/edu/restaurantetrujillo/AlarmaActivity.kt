package com.tecsup.edu.restaurantetrujillo

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AlarmaActivity : AppCompatActivity() {

    private lateinit var alarmManager: AlarmManager
    private lateinit var alarmIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarmas)

        supportActionBar?.hide()

        // Obtener referencias a los elementos de la interfaz de usuario
        val buttonSetAlarm: Button = findViewById(R.id.buttonSetAlarm)

        // Obtener una instancia del servicio de alarma
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Crear un intent para el BroadcastReceiver de la alarma
        val intent = Intent(this, AlarmReceiver::class.java)
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // Configurar el clic del botón para establecer la alarma
        buttonSetAlarm.setOnClickListener {
            showDateTimePicker()
        }
    }

    private fun showDateTimePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            calendar.set(selectedYear, selectedMonth, selectedDay)
            val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                calendar.set(Calendar.MINUTE, selectedMinute)

                setAlarm(calendar)
            }, hour, minute, true)
            timePickerDialog.show()
        }, year, month, day)
        datePickerDialog.show()
    }

    private fun setAlarm(calendar: Calendar) {
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmIntent)
    }
}

