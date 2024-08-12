package com.example.heartstonetestapp.presentation.util

import com.example.heartstonetestapp.data.models.Card
import com.example.heartstonetestapp.presentation.models.CardUI

object Mapper {
  fun Card.toUiModel(): CardUI = CardUI(
    cardId = this.cardId,
    name = this.name,
    cost = this.cost,
    description = this.text,
    playerClass = this.playerClass,
    image = this.image,
    isFavourite = this.isFavourite
  )
}
