package com.appkotlin.service

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import com.appkotlin.R
import com.appkotlin.activities.HomeActivity
import com.appkotlin.activities.LoginActivity
import com.appkotlin.service.Constants.CHANNEL_ID
import com.appkotlin.service.Constants.MUSIC_NOTIFICATION_ID

class MyService: Service() {

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val serviceChannel=NotificationChannel(CHANNEL_ID,"My Service channel",NotificationManager.IMPORTANCE_DEFAULT)
        val manager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(serviceChannel)

    }



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showNotification()
        return START_STICKY
    }

    private fun showNotification() {
        val notificationIntent=Intent(this,LoginActivity::class.java)
        val pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0)
        val notification=Notification.Builder(this, CHANNEL_ID).apply {
            setContentTitle("Notification title")
            setContentText("This is notification for your service")
            setContentIntent(pendingIntent)
            setSmallIcon(R.drawable.ic__user)
        }.build()
        startForeground(MUSIC_NOTIFICATION_ID,notification)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                Toast.makeText(applicationContext,"Hello From Service",Toast.LENGTH_LONG).show()
            },10000
        )
    }
}