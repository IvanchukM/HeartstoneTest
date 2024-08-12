package com.example.heartstonetestapp

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.example.heartstonetestapp.data.notification.NotificationHelper
import com.example.heartstonetestapp.di.HiltWorkerFactoryEntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CardsApplication : Application(), Configuration.Provider {

  @Inject
  lateinit var notificationHelper: NotificationHelper

  override fun onCreate() {
    super.onCreate()
    notificationHelper.createNotificationChannel()
  }

  override val workManagerConfiguration: Configuration = Configuration.Builder()
    .setMinimumLoggingLevel(Log.DEBUG)
    .setWorkerFactory(EntryPoints.get(this, HiltWorkerFactoryEntryPoint::class.java).workerFactory())
    .build()
}
