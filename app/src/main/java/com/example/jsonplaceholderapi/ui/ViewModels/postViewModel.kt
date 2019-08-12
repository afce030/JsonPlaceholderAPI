package com.example.jsonplaceholderapi.ui.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity
import com.example.jsonplaceholderapi.di.Repositories.PostsRepo

class postViewModel(application: Application) : AndroidViewModel(application){

    private var posts : LiveData<List<PostEntity>>? = null
    private val repo = PostsRepo.getRepoInstance(application)

    fun getAllPostViewModel(): LiveData<List<PostEntity>>{
        posts = repo!!.getPostsFromRepo()
        return posts!!
    }

}