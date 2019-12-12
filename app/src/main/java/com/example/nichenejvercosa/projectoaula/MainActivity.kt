package com.example.nichenejvercosa.projectoaula

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonRegister = findViewById<Button>(R.id.btn_register)
        val buttonLogin = findViewById<Button>(R.id.btn_login)

        buttonRegister.setOnClickListener {
            val intentRegister = Intent(this, Register::class.java)
            startActivity(intentRegister)
        }

        buttonLogin.setOnClickListener {
            val intentLogin = Intent(this, Login::class.java)
            startActivity(intentLogin)
        }


    }

    override fun onResume() {
        super.onResume()

        // Usando pref apenas por estudo
        val sharedPref = getSharedPreferences("MyPrefs", 0)
        val isAlarmSet = sharedPref.getString("AlarmSet", "false")

        if (isAlarmSet.equals("false")){
            val sharedPrefEditor = sharedPref.edit()
            sharedPrefEditor.putString("AlarmSet", "true")
            sharedPrefEditor.apply()

            setRecurringAlarm(this)
        }

    }

    private fun setRecurringAlarm(context: Context) {

        val updateTime = Calendar.getInstance()
        updateTime.setTimeZone(TimeZone.getTimeZone("GMT"))
        updateTime.set(Calendar.HOUR_OF_DAY, 10)
        updateTime.set(Calendar.MINUTE, 15)

        val notificationIntent = Intent(context, AlarmReceiver::class.java)

        val recurringNotification = PendingIntent.getBroadcast(context,
                0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        val alarms = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                updateTime.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, recurringNotification)
    }


}
