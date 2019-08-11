package com.example.jsonplaceholderapi.API_connections

import com.example.jsonplaceholderapi.POJO_classes.PostDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsWS {

    @GET("post/{id}")
    fun getPostByID(@Path("id") number: Int) : Call<List<PostDTO>>

}