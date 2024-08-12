package com.example.heartstonetestapp.di

import android.content.Context
import com.example.heartstonetestapp.data.notification.NotificationHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

  @Singleton
  @Provides
  fun provideNotificationHelper(@ApplicationContext context: Context): NotificationHelper {
    return NotificationHelper(context)
  }
}