package com.example.heartstonetestapp.data.models

data class Card(
  val cardId: String,
  val dbfId: Int,
  val name: String,
  val cardSet: String?,
  val type: String?,
  val rarity: String?,
  val cost: Int?,
  val attack: Int?,
  val health: Int?,
  val text: String?,
  val flavor: String?,
  val artist: String?,
  val collectible: Boolean?,
  val race: String?,
  val playerClass: String?,
  val image: String?,
  val locale: String?,
  val spellSchool: String?,
  val isFavourite: Boolean,
)
