package com.example.jsonplaceholderapi.di.Repositories

import com.example.jsonplaceholderapi.API_connections.PostsWS
import javax.inject.Inject

class PostsRepo {

    @Inject
    lateinit var postsWS: PostsWS//This parameter is injected by the Retrofit Module

    //Creating singleton
    companion object {
        val instance = PostsRepo()
    }

}