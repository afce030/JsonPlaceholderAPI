package com.example.jsonplaceholderapi.ui.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jsonplaceholderapi.LocalData.RoomEntities.FavoritesEntity
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity
import com.example.jsonplaceholderapi.LocalData.RoomEntities.UserEntity
import com.example.jsonplaceholderapi.di.Repositories.PostsRepo

class postViewModel(application: Application) : AndroidViewModel(application){

    private var posts : LiveData<List<PostEntity>>? = null
    private var favorites : LiveData<List<FavoritesEntity>>? = null
    private var users : LiveData<List<UserEntity>>? = null
    private val repo = PostsRepo.getRepoInstance(application)

    fun refresh(){
        repo!!.refreshPosts()
    }

    fun delete(){
        repo!!.deletePosts()
    }

    fun getAllPostViewModel(): LiveData<List<PostEntity>>{
        posts = repo!!.getPostsFromRepo()
        return posts!!
    }

    fun getFavoritesViewModel(): LiveData<List<FavoritesEntity>>{
        favorites = repo!!.getFavoritesFromRepo()
        return favorites!!
    }

    fun inserFavoriteVM(postEntity: PostEntity){
        repo!!.insertFavorite(postEntity)
    }

    fun getAllUsersViewModel(): LiveData<List<UserEntity>>{
        users = repo!!.getUsersFromRepo()
        return users!!
    }

}