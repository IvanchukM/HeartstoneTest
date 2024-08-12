package com.example.heartstonetestapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.heartstonetestapp.database.models.FavouriteCardDBO

@Dao
interface FavouriteCardsDao {

  @Query("SELECT * FROM favouriteCards")
  suspend fun getFavouriteCards(): List<FavouriteCardDBO>

  @Query("SELECT cardId FROM favouriteCards")
  suspend fun getFavouriteCardsId(): List<String>

  @Insert
  suspend fun insert(card: FavouriteCardDBO)

  @Query("DELETE FROM favouriteCards WHERE cardId = :cardId")
  suspend fun delete(cardId: String)

  @Query("DELETE FROM favouriteCards")
  suspend fun clean()

}
