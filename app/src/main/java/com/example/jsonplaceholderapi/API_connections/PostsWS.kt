package com.example.jsonplaceholderapi.API_connections

import com.example.jsonplaceholderapi.POJO_classes.PostDTO
import com.example.jsonplaceholderapi.POJO_classes.UsersDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsWS {

    @GET("posts/")
    fun getPostsFromAPI() : Call<List<PostDTO>>

    @GET("users/")
    fun getUsersFromAPI() : Call<List<UsersDTO>>

}