package com.example.heartstonetestapp.data

import com.example.heartstonetestapp.data.models.Card
import com.example.heartstonetestapp.data.util.RequestResult
import kotlinx.coroutines.flow.Flow

interface RemoteCardsRepository {

  suspend fun loadCards(): RequestResult<Unit>

  suspend fun getCardByName(cardName: String): Flow<RequestResult<List<Card>>>

}
