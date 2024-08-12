package com.example.heartstonetestapp.network

import com.example.heartstonetestapp.network.models.CardDTO
import retrofit2.http.GET
import retrofit2.http.Path

private object CardsUrls {
  const val GET_CARDS = "/cards"

  const val GET_CARDS_BY_CLASS = "/cards/classes/{class}"

  const val GET_CARDS_BY_NAME = "/cards/search/{name}"
}

interface CardsApi {
  @GET(CardsUrls.GET_CARDS)
  suspend fun getCards(): Result<Map<String, List<CardDTO>>>

  @GET(CardsUrls.GET_CARDS_BY_CLASS)
  suspend fun getCardsByClass(@Path("class") className: String): Result<List<CardDTO>>

  @GET(CardsUrls.GET_CARDS_BY_NAME)
  suspend fun getCardsByName(@Path("name") cardName: String): Result<List<CardDTO>>
}
