package com.example.heartstonetestapp.data.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.heartstonetestapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val CHANNEL_ID = "cardsWorker"
private const val CHANNEL_NAME = "cardsWorkerName"
class NotificationHelper @Inject constructor(
  @ApplicationContext private val context: Context
) {

  fun createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        CHANNEL_ID,
        CHANNEL_NAME,
        NotificationManager.IMPORTANCE_HIGH
      )
      val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
      manager.createNotificationChannel(channel)
    }
  }

  fun showNotification(message: String) {
    val notificationManager =
      context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setContentText(message)
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
      .setSmallIcon(R.drawable.ic_download)
      .setContentTitle(context.getString(R.string.work_manager_notification_title))
      .setAutoCancel(true)
      .build()
    notificationManager.notify(1, notificationBuilder)
  }
}