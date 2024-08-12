package com.example.heartstonetestapp.presentation.util

import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

private const val WORK_NAME = "updateCards"

object WorkManagerUtils {
  fun WorkManager.begin(downloadRequest: OneTimeWorkRequest) {
    this.beginUniqueWork(
      WORK_NAME,
      ExistingWorkPolicy.KEEP,
      downloadRequest
    )
      .enqueue()
  }
}
