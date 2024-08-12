package com.example.heartstonetestapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.heartstonetestapp.database.models.CardDBO

@Dao
interface CardsDao {

  @Query("SELECT * FROM cards")
  suspend fun getAll(): List<CardDBO>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(vararg card: CardDBO)

  @Query("SELECT * FROM cards WHERE cardId = :cardId")
  suspend fun getCardById(cardId: String): CardDBO?

  @Query("DELETE FROM favouriteCards")
  suspend fun clean()

}