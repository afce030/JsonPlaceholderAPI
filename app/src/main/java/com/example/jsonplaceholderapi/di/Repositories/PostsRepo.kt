package com.example.jsonplaceholderapi.di.Repositories

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.jsonplaceholderapi.API_connections.PostsWS
import com.example.jsonplaceholderapi.App
import com.example.jsonplaceholderapi.LocalData.RoomDAO.FavoritesDAO
import com.example.jsonplaceholderapi.LocalData.RoomDAO.PostsDAO
import com.example.jsonplaceholderapi.LocalData.RoomDAO.UsersDAO
import com.example.jsonplaceholderapi.LocalData.RoomDatabases.PostDatabase
import com.example.jsonplaceholderapi.LocalData.RoomEntities.FavoritesEntity
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity
import com.example.jsonplaceholderapi.LocalData.RoomEntities.UserEntity
import com.example.jsonplaceholderapi.POJO_classes.PostDTO
import com.example.jsonplaceholderapi.POJO_classes.UsersDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostsRepo(application: Application) {

    var postsDAO: PostsDAO = PostDatabase.getPostDataBase(application)!!.postsDao()
    var foundPosts: LiveData<List<PostEntity>> = postsDAO.getAllPostsFromRoom()

    var favoritesDAO: FavoritesDAO = PostDatabase.getPostDataBase(application)!!.favoritesDao()
    var favoritePosts: LiveData<List<FavoritesEntity>> = favoritesDAO.getAllFavoritesFromRoom()

    var usersDAO: UsersDAO = PostDatabase.getPostDataBase(application)!!.usersDao()
    var foundUsers: LiveData<List<UserEntity>> = usersDAO.getAllUsersFromRoom()

    @Inject
    lateinit var postsWS: PostsWS//This parameter is injected by the Retrofit Module

    //Creating singleton with parameters
    companion object {
        var instance: PostsRepo? = null

        fun getRepoInstance(application: Application): PostsRepo? {
            if (instance == null) {
                synchronized(PostsRepo::class) {
                    instance = PostsRepo(application)
                    (application as App).getRetrofitComponent().inject(instance!!)
                }
            }
            return instance
        }

    }


    fun getFavoritesFromRepo(): LiveData<List<FavoritesEntity>>{
        favoritePosts = favoritesDAO.getAllFavoritesFromRoom()
        return favoritePosts
    }

    fun insertFavorite(postEntity: PostEntity){
        val favorite: FavoritesEntity = FavoritesEntity(postEntity.id,postEntity.userId,
            postEntity.title,postEntity.body)
        InsertFavoriteAsyncTask(favoritesDAO).execute(favorite)
    }

    private class InsertFavoriteAsyncTask(val favoritesDAO: FavoritesDAO) :
        AsyncTask<FavoritesEntity, Void, Void>() {
        override fun doInBackground(vararg favoritesEntity: FavoritesEntity?): Void? {
            for(post in favoritesEntity) {
                if (post != null) favoritesDAO.insert(post)
            }
            return null
        }

    }

    fun getPostsFromRepo(): LiveData<List<PostEntity>>{
        //refreshPosts()
        foundPosts = postsDAO.getAllPostsFromRoom()
        return foundPosts
    }

    fun deletePosts(){
        deletePostAsyncTask(postsDAO).execute()
    }

    fun refreshPosts(){
        val call = postsWS.getPostsFromAPI()
        call.enqueue(object : Callback<List<PostDTO>>{
            override fun onResponse(call: Call<List<PostDTO>>, response: Response<List<PostDTO>>) {
                if(response.isSuccessful){
                    val posts: List<PostDTO> = response.body()!!
                    //Insert movie by room
                    for(p in posts){
                        val f = PostEntity(p.id!!, p.userId.toString(), p.title!!, p.body!!)
                        InsertPostAsyncTask(postsDAO).execute(f)
                    }
                }
            }

            override fun onFailure(call: Call<List<PostDTO>>, t: Throwable) {
            }
        })
    }

    private class InsertPostAsyncTask(val postsDAO: PostsDAO) :
        AsyncTask<PostEntity, Void, Void>() {
        override fun doInBackground(vararg postEntity: PostEntity?): Void? {
            for(post in postEntity) {
                if (post != null) postsDAO.insert(post)
            }
            return null
        }

    }

    private class deletePostAsyncTask(val postsDAO: PostsDAO) :
        AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            postsDAO.deleteAll()
            return null
        }
    }

    fun getUsersFromRepo(): LiveData<List<UserEntity>>{
        refreshUsers()
        foundUsers = usersDAO.getAllUsersFromRoom()
        return foundUsers
    }

    fun refreshUsers(){
        val call = postsWS.getUsersFromAPI()
        call.enqueue(object : Callback<List<UsersDTO>>{
            override fun onResponse(call: Call<List<UsersDTO>>, response: Response<List<UsersDTO>>) {
                if(response.isSuccessful){
                    val users: List<UsersDTO> = response.body()!!
                    //Insert movie by room
                    for(u in users){
                        val f = UserEntity(u.id,u.website,u.address!!.zipcode,u.address.geo!!.lng,
                            u.address.geo.lat,u.address.suite,u.address.city,u.address.street,u.phone,
                            u.name,u.company!!.bs,u.company.catchPhrase,u.company.name,u.email,u.username)
                        InsertUserAsyncTask(usersDAO).execute(f)
                    }
                }
            }

            override fun onFailure(call: Call<List<UsersDTO>>, t: Throwable) {
            }
        })
    }

    private class InsertUserAsyncTask(val usersDAO: UsersDAO) :
        AsyncTask<UserEntity, Void, Void>() {
        override fun doInBackground(vararg userEntity: UserEntity?): Void? {
            for(user in userEntity) {
                if (user != null) usersDAO.insert(user)
            }
            return null
        }

    }

}