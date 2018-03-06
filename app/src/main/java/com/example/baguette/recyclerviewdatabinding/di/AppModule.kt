package com.example.baguette.recyclerviewdatabinding.di

import android.content.Context
import com.example.baguette.recyclerviewdatabinding.AppApplication
import com.example.baguette.recyclerviewdatabinding.viewmovel.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val app: AppApplication) {
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideMainViewModel(): MainViewModel = MainViewModel()
}
