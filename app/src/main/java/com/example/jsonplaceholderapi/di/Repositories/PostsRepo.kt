package com.example.jsonplaceholderapi.di.Repositories

import androidx.lifecycle.LiveData
import com.example.jsonplaceholderapi.API_connections.PostsWS
import com.example.jsonplaceholderapi.LocalData.RoomDAO.PostsDAO
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity
import com.example.jsonplaceholderapi.POJO_classes.PostDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostsRepo(postsDAO: PostsDAO) {

    @Inject
    lateinit var postsWS: PostsWS//This parameter is injected by the Retrofit Module

    //Creating singleton
    companion object {
        val instance = PostsRepo()
    }

    fun getPostByIDS(id: Int): LiveData<List<PostEntity>>{
        refreshPosts(id)
        return
    }

    fun refreshPosts(id: Int){
        val call = postsWS.getPostByID(id)
        call.enqueue(object : Callback<PostDTO>{
            override fun onResponse(call: Call<PostDTO>, response: Response<PostDTO>) {

                if(response.isSuccessful){

                    val posts: List<PostDTO> = response.body()

                }

            }
            override fun onFailure(call: Call<PostDTO>, t: Throwable) {
            }
        })
    }

}