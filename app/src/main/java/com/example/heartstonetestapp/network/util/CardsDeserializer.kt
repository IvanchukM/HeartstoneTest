package com.example.heartstonetestapp.network.util

import com.example.heartstonetestapp.network.models.CardDTO
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

object CardsResponseDeserializer : KSerializer<List<CardDTO>> {

  override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CardsResponse")

  override fun deserialize(decoder: Decoder): List<CardDTO> {
    val jsonDecoder = decoder as? JsonDecoder
      ?: throw IllegalStateException("This deserializer can only be used with JSON format")
    val jsonObject = jsonDecoder.decodeJsonElement() as JsonObject

    // Извлекаем все значения из JSON-объекта и объединяем их в один список
    return jsonObject.values.flatMap { jsonElement ->
      jsonDecoder.json.decodeFromJsonElement<List<CardDTO>>(jsonElement)
    }
  }

  override fun serialize(encoder: Encoder, value: List<CardDTO>) {}
}

//class JsonConverterFactory(private val json: Json) : Converter.Factory() {
//  override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, *> {
//    return JsonResponseBodyConverter(json, type)
//  }
//}
//
//class JsonResponseBodyConverter(private val json: Json, private val type: Type) : Converter<ResponseBody, List<CardDTO>> {
//  override fun convert(value: ResponseBody): List<CardDTO> {
//    val jsonString = value.string()
//    val jsonObject = json.parseToJsonElement(jsonString).jsonObject
//    val cardList = jsonObject.values.joinToString(prefix = "[", postfix = "]") {
//      it.jsonArray.toString()
//    }
//    return json.decodeFromString(type, cardList)
//  }
//}