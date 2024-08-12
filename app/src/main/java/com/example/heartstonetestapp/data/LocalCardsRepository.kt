package com.example.heartstonetestapp.data

import com.example.heartstonetestapp.data.models.Card
import com.example.heartstonetestapp.data.util.RequestResult
import com.example.heartstonetestapp.database.models.CardDBO
import kotlinx.coroutines.flow.Flow

interface LocalCardsRepository {

  suspend fun getAllCards(): Flow<RequestResult<List<Card>>>

  suspend fun saveCardToFavourite(cardId: String): Result<Unit>

  suspend fun removeCardFromFavourite(cardId: String): Result<Unit>

  suspend fun getFavouriteCards(): Flow<RequestResult<List<Card>>>

  suspend fun getFavouriteCardsIds(): Set<String>

  suspend fun uploadCardsToDb(cards: List<CardDBO>)

  fun getInitialLoadState(): Boolean

  fun setLoadState()
}
