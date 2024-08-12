package com.example.heartstonetestapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.heartstonetestapp.BuildConfig
import com.example.heartstonetestapp.database.CardsDatabase
import com.example.heartstonetestapp.network.CardsApi
import com.example.heartstonetestapp.network.util.HeaderInterceptor
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val SHARED_PREF_NAME = "cardsPref"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
    context.getSharedPreferences(
      SHARED_PREF_NAME,
      Context.MODE_PRIVATE
    )

  @Provides
  @Singleton
  fun providesJson(): Json = Json {
    ignoreUnknownKeys = true
  }

  @Singleton
  @Provides
  fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }

  @Singleton
  @Provides
  fun provideOkHttpHeaderInterceptor(): Interceptor = HeaderInterceptor()

  @Singleton
  @Provides
  fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, headerInterceptor: Interceptor): OkHttpClient =
    OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .addInterceptor(headerInterceptor)
      .readTimeout(300, TimeUnit.SECONDS)
      .build()


  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit =
    Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
      .addCallAdapterFactory(ResultCallAdapterFactory.create())
      .client(okHttpClient)
      .build()

  @Singleton
  @Provides
  fun provideCardsApi(retrofit: Retrofit): CardsApi = retrofit.create(CardsApi::class.java)


  @Singleton
  @Provides
  fun provideCardsDatabase(@ApplicationContext context: Context) = CardsDatabase(context)
}
