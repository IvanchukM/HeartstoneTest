package com.example.heartstonetestapp.data

import android.content.SharedPreferences
import android.util.Log
import com.example.heartstonetestapp.data.models.Card
import com.example.heartstonetestapp.data.util.Mappers.toCard
import com.example.heartstonetestapp.data.util.Mappers.toFavouriteDBO
import com.example.heartstonetestapp.data.util.RequestResult
import com.example.heartstonetestapp.database.CardsDatabase
import com.example.heartstonetestapp.database.models.CardDBO
import com.example.heartstonetestapp.database.models.FavouriteCardDBO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val INITIAL_LOAD_FLAG = "initialLoad"

class LocalCardsRepositoryImpl @Inject constructor(
  private val cardsDatabase: CardsDatabase,
  private val sharedPreferences: SharedPreferences,
) : LocalCardsRepository {

  override suspend fun getAllCards(): Flow<RequestResult<List<Card>>> {
    val favouriteCardsId = getFavouriteCardsIds()
    return cardsDatabase.cardsDAO::getAll.asFlow()
      .map<List<CardDBO>, RequestResult<List<Card>>> { cardsList ->
        RequestResult.Success(cardsList.map { card ->
          card.toCard(
            favouriteCardsId.contains(card.cardId)
          )
        })
      }
      .catch { emit(RequestResult.Error) }
  }

  override suspend fun saveCardToFavourite(cardId: String): Result<Unit> {
    return try {
      val cardData = cardsDatabase.cardsDAO.getCardById(cardId)
      cardData?.let { cardsDatabase.favouriteCardsDAO.insert(it.toFavouriteDBO()) }
      Result.success(Unit)
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  override suspend fun removeCardFromFavourite(cardId: String): Result<Unit> {
    return try {
      cardsDatabase.favouriteCardsDAO.delete(cardId)
      Result.success(Unit)
    } catch (e: Exception) {
      Log.d("TAG", "removeCardFromFavourite: $e")
      Result.failure(e)
    }
  }

  override suspend fun getFavouriteCards(): Flow<RequestResult<List<Card>>> {
    return cardsDatabase.favouriteCardsDAO::getFavouriteCards.asFlow()
      .map<List<FavouriteCardDBO>, RequestResult<List<Card>>> { cardsList -> RequestResult.Success(cardsList.map { it.toCard() }) }
      .catch { emit(RequestResult.Error) }
  }

  override suspend fun getFavouriteCardsIds(): Set<String> {
    return try {
      cardsDatabase.favouriteCardsDAO.getFavouriteCardsId().toSet()
    } catch (e: Exception) {
      emptySet()
    }
  }

  override suspend fun uploadCardsToDb(cards: List<CardDBO>) {
    cardsDatabase.cardsDAO.insertAll(*cards.toTypedArray())
  }

  override fun getInitialLoadState(): Boolean = sharedPreferences.getBoolean(INITIAL_LOAD_FLAG, false)

  override fun setLoadState() = sharedPreferences.edit().putBoolean(INITIAL_LOAD_FLAG, true).apply()

}
