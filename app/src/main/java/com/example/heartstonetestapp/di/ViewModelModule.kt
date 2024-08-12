package com.example.heartstonetestapp.di

import com.example.heartstonetestapp.presentation.screens.CardsSharedViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

  @Provides
  @Singleton
  fun provideSharedViewModel(
  ): CardsSharedViewModel {
    return CardsSharedViewModel()
  }
}
