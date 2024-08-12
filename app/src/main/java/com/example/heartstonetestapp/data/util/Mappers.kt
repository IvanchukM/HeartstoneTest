package com.example.heartstonetestapp.data.util

import com.example.heartstonetestapp.data.models.Card
import com.example.heartstonetestapp.database.models.CardDBO
import com.example.heartstonetestapp.database.models.FavouriteCardDBO
import com.example.heartstonetestapp.network.models.CardDTO


object Mappers {

  fun CardDTO.toCard(isFavourite: Boolean) = Card(
    cardId = this.cardId,
    dbfId = this.dbfId,
    name = this.name,
    cardSet = this.cardSet,
    type = this.type,
    rarity = this.rarity,
    cost = this.cost,
    attack = this.attack,
    health = this.health,
    text = this.text,
    flavor = this.flavor,
    artist = this.artist,
    collectible = this.collectible,
    race = this.race,
    playerClass = this.playerClass,
    image = this.image,
    locale = this.locale,
    spellSchool = this.spellSchool,
    isFavourite = isFavourite,
  )

  fun CardDTO.toCardDBO() = CardDBO(
    cardId = this.cardId,
    dbfId = this.dbfId,
    name = this.name,
    cardSet = this.cardSet,
    type = this.type,
    rarity = this.rarity,
    cost = this.cost,
    attack = this.attack,
    health = this.health,
    text = this.text,
    flavor = this.flavor,
    artist = this.artist,
    collectible = this.collectible,
    race = this.race,
    playerClass = this.playerClass,
    image = this.image,
    locale = this.locale,
    spellSchool = this.spellSchool,
  )

  fun FavouriteCardDBO.toCard() = Card(
    cardId = this.cardId,
    dbfId = this.dbfId,
    name = this.name,
    cardSet = this.cardSet,
    type = this.type,
    rarity = this.rarity,
    cost = this.cost,
    attack = this.attack,
    health = this.health,
    text = this.text,
    flavor = this.flavor,
    artist = this.artist,
    collectible = this.collectible,
    race = this.race,
    playerClass = this.playerClass,
    image = this.image,
    locale = this.locale,
    spellSchool = this.spellSchool,
    isFavourite = true,
  )

  fun CardDBO.toFavouriteDBO() = FavouriteCardDBO(
    cardId = this.cardId,
    dbfId = this.dbfId,
    name = this.name,
    cardSet = this.cardSet,
    type = this.type,
    rarity = this.rarity,
    cost = this.cost,
    attack = this.attack,
    health = this.health,
    text = this.text,
    flavor = this.flavor,
    artist = this.artist,
    collectible = this.collectible,
    race = this.race,
    playerClass = this.playerClass,
    image = this.image,
    locale = this.locale,
    spellSchool = this.spellSchool,
  )

  fun CardDBO.toCard(isFavourite: Boolean) = Card(
    cardId = this.cardId,
    dbfId = this.dbfId,
    name = this.name,
    cardSet = this.cardSet,
    type = this.type,
    rarity = this.rarity,
    cost = this.cost,
    attack = this.attack,
    health = this.health,
    text = this.text,
    flavor = this.flavor,
    artist = this.artist,
    collectible = this.collectible,
    race = this.race,
    playerClass = this.playerClass,
    image = this.image,
    locale = this.locale,
    spellSchool = this.spellSchool,
    isFavourite = isFavourite,
  )
}