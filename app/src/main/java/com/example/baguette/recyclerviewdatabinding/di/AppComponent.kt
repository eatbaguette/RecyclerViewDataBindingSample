package com.example.baguette.recyclerviewdatabinding.di

import com.example.baguette.recyclerviewdatabinding.views.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class])
interface AppComponent {
    fun inject(target: MainActivity)
}
