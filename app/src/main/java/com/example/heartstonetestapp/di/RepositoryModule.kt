package com.example.heartstonetestapp.di

import com.example.heartstonetestapp.data.RemoteCardsRepository
import com.example.heartstonetestapp.data.RemoteCardsRepositoryImpl
import com.example.heartstonetestapp.data.LocalCardsRepository
import com.example.heartstonetestapp.data.LocalCardsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

  @Binds
  fun bindCardsRepository(cardsRepositoryImpl: RemoteCardsRepositoryImpl): RemoteCardsRepository

  @Binds
  fun bindsMainRepository(mainRepositoryImpl: LocalCardsRepositoryImpl): LocalCardsRepository
}
