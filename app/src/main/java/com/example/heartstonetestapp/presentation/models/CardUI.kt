package com.example.heartstonetestapp.presentation.models

data class CardUI(
  val cardId: String,
  val name: String,
  val cost: Int?,
  val description: String?,
  val playerClass: String?,
  val image: String?,
  val isFavourite: Boolean
)
