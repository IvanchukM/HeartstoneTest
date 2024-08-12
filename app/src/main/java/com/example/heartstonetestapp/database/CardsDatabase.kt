package com.example.heartstonetestapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.heartstonetestapp.database.dao.CardsDao
import com.example.heartstonetestapp.database.dao.FavouriteCardsDao
import com.example.heartstonetestapp.database.models.CardDBO
import com.example.heartstonetestapp.database.models.FavouriteCardDBO

class CardsDatabase(private val database: CardsRoomDatabase) {
  val favouriteCardsDAO: FavouriteCardsDao
    get() = database.favouriteCardsDao()

  val cardsDAO: CardsDao
    get() = database.cardsDao()

}

@Database(entities = [FavouriteCardDBO::class, CardDBO::class], version = 1)
abstract class CardsRoomDatabase : RoomDatabase() {
  abstract fun favouriteCardsDao(): FavouriteCardsDao
  abstract fun cardsDao(): CardsDao
}

fun CardsDatabase(applicationContext: Context): CardsDatabase {
  val cardsRoomDatabase =
    Room.databaseBuilder(
      checkNotNull(applicationContext.applicationContext),
      CardsRoomDatabase::class.java,
      "cards"
    ).build()
  return CardsDatabase(cardsRoomDatabase)
}