package com.example.jsonplaceholderapi.di.DaggerModules

import com.example.jsonplaceholderapi.API_connections.PostsWS
import com.example.jsonplaceholderapi.Utilities.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object RetrofitModule {

    @Provides
    fun provideGsonConverter(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesRetrofitService(gson: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gson)
            .build()
    }

    @Provides
    fun providePosts(retrofit: Retrofit): PostsWS{
        return retrofit.create(PostsWS::class.java)
    }

}