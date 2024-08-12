package com.example.heartstonetestapp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardDTO(
  @SerialName("cardId") val cardId: String,
  @SerialName("dbfId") val dbfId: Int,
  @SerialName("name") val name: String,
  @SerialName("cardSet") val cardSet: String? = null,
  @SerialName("type") val type: String? = null,
  @SerialName("rarity") val rarity: String? = null,
  @SerialName("cost") val cost: Int? = null,
  @SerialName("attack") val attack: Int? = null,
  @SerialName("health") val health: Int? = null,
  @SerialName("text") val text: String? = null,
  @SerialName("flavor") val flavor: String? = null,
  @SerialName("artist") val artist: String? = null,
  @SerialName("collectible") val collectible: Boolean? = null,
  @SerialName("race") val race: String? = null,
  @SerialName("playerClass") val playerClass: String? = null,
  @SerialName("img") val image: String? = null,
  @SerialName("locale") val locale: String? = null,
  @SerialName("spellSchool") val spellSchool: String? = null,
)
