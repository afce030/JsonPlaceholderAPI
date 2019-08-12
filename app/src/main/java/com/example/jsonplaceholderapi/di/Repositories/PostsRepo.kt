package com.example.jsonplaceholderapi.di.Repositories

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.jsonplaceholderapi.API_connections.PostsWS
import com.example.jsonplaceholderapi.LocalData.RoomDAO.PostsDAO
import com.example.jsonplaceholderapi.LocalData.RoomDatabases.PostDatabase
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity
import com.example.jsonplaceholderapi.POJO_classes.PostDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostsRepo(application: Application) {

    var postsDAO: PostsDAO = PostDatabase.instance
    var foundPost: LiveData<List<PostEntity>>

    @Inject
    lateinit var postsWS: PostsWS//This parameter is injected by the Retrofit Module

    //Creating singleton with parameters
    companion object {
        var instance: PostsRepo? = null

        fun getRepoInstance(Application: Application ,PostsDAO: PostsDAO, FoundPost: LiveData<List<PostEntity>>): PostsRepo? {
            if (instance == null) {
                synchronized(PostsRepo::class) {
                    instance = PostsRepo(Application)
                }
            }
            return instance
        }

    }

    fun getPostByIDS(id: Int): LiveData<List<PostEntity>>{
        refreshPosts(id)
        val data: LiveData<List<PostEntity>> = postsDAO.getAllPosts()
        return data
    }

    fun refreshPosts(id: Int){
        val call = postsWS.getPostByID(id)
        call.enqueue(object : Callback<List<PostDTO>>{
            override fun onResponse(call: Call<List<PostDTO>>, response: Response<List<PostDTO>>) {
                if(response.isSuccessful){
                    val posts: List<PostDTO>? = response.body()
                    //Insert movie by room
                }
            }

            override fun onFailure(call: Call<List<PostDTO>>, t: Throwable) {
            }
        })
    }

    private class InsertAsyncTask(val postsDAO: PostsDAO) :
        AsyncTask<PostEntity, Void, Void>() {
        override fun doInBackground(vararg postEntity: PostEntity?): Void? {
            for(post in postEntity) {
                if (post != null) postsDAO.insert(post)
            }
            return null
        }

    }

}