package com.example.jsonplaceholderapi.di.DaggerComponents

import com.example.jsonplaceholderapi.di.DaggerModules.RetrofitModule
import com.example.jsonplaceholderapi.di.Repositories.PostsRepo
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {
    fun inject(postsRepo: PostsRepo)
}