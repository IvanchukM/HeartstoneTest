package com.example.heartstonetestapp.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardDBO(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  @ColumnInfo("cardId") val cardId: String,
  @ColumnInfo("dbfId") val dbfId: Int,
  @ColumnInfo("name") val name: String,
  @ColumnInfo("cardSet") val cardSet: String?,
  @ColumnInfo("type") val type: String?,
  @ColumnInfo("rarity") val rarity: String?,
  @ColumnInfo("cost") val cost: Int?,
  @ColumnInfo("attack") val attack: Int?,
  @ColumnInfo("health") val health: Int?,
  @ColumnInfo("text") val text: String?,
  @ColumnInfo("flavor") val flavor: String?,
  @ColumnInfo("artist") val artist: String?,
  @ColumnInfo("collectible") val collectible: Boolean?,
  @ColumnInfo("race") val race: String?,
  @ColumnInfo("playerClass") val playerClass: String?,
  @ColumnInfo("image") val image: String?,
  @ColumnInfo("locale") val locale: String?,
  @ColumnInfo("spellSchool") val spellSchool: String?,
)