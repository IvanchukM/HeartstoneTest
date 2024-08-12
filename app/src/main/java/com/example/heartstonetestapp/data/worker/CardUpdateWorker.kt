package com.example.heartstonetestapp.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.heartstonetestapp.R
import com.example.heartstonetestapp.data.RemoteCardsRepository
import com.example.heartstonetestapp.data.notification.NotificationHelper
import com.example.heartstonetestapp.data.util.RequestResult
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class CardUpdateWorker @AssistedInject constructor(
  @Assisted private val context: Context,
  @Assisted params: WorkerParameters,
  private val remoteCardsRepository: RemoteCardsRepository,
  private val notificationHelper: NotificationHelper
) : CoroutineWorker(context, params) {
  override suspend fun doWork(): Result {
    notificationHelper.showNotification(message = context.getString(R.string.work_manager_download_started))
    val result = withContext(Dispatchers.IO) {
      remoteCardsRepository.loadCards()
    }
    return when (result) {
      RequestResult.Error -> {
        notificationHelper.showNotification(message = context.getString(R.string.work_manager_error))
        Result.failure()
      }

      is RequestResult.Success -> {
        notificationHelper.showNotification(message = context.getString(R.string.work_manager_success))
        Result.success()
      }
    }
  }
}
