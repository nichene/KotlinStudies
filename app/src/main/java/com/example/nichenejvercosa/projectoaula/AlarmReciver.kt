package com.example.nichenejvercosa.projectoaula

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        callNotification(context)
    }

    private fun callNotification(context: Context){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(context, 1234,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context,"MYAPP")
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.setContentTitle("My App Notification")
        builder.setContentText("USE O APP. CLIQUE AQUI.")
        builder.setPriority(NotificationCompat.PRIORITY_HIGH)
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel( "MYAPP", "My Application", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notification = builder.build()
        notificationManager.notify(1234, notification)

        }
}