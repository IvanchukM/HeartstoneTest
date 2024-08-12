package com.example.heartstonetestapp.data

import com.example.heartstonetestapp.data.models.Card
import com.example.heartstonetestapp.data.util.Mappers.toCard
import com.example.heartstonetestapp.data.util.Mappers.toCardDBO
import com.example.heartstonetestapp.data.util.RequestResult
import com.example.heartstonetestapp.network.CardsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteCardsRepositoryImpl @Inject constructor(
  private val cardsApi: CardsApi,
  private val localCardsRepository: LocalCardsRepository
) : RemoteCardsRepository {

  override suspend fun loadCards(): RequestResult<Unit> {
    return try {
      val cardsResult = cardsApi.getCards()
      if (cardsResult.isSuccess) {
        val cards = cardsResult.getOrThrow().values.flatten().map { it.toCardDBO() }
        localCardsRepository.uploadCardsToDb(cards)
      }
      RequestResult.Success(Unit)
    } catch (e: Exception) {
      RequestResult.Error
    }
  }

  override suspend fun getCardByName(cardName: String): Flow<RequestResult<List<Card>>> {
    val apiResult = cardsApi.getCardsByName(cardName)
    val favouriteCardsId = localCardsRepository.getFavouriteCardsIds()
    return flow {
      if (apiResult.isSuccess) {
        emit(
          RequestResult.Success(
            apiResult.getOrThrow().map { card -> card.toCard(favouriteCardsId.contains(card.cardId)) })
        )
      } else {
        emit(RequestResult.Error)
      }
    }
  }
}
