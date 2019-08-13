package com.example.jsonplaceholderapi.LocalData.RoomDatabases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jsonplaceholderapi.LocalData.RoomDAO.PostsDAO
import com.example.jsonplaceholderapi.LocalData.RoomDAO.UsersDAO
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity
import com.example.jsonplaceholderapi.LocalData.RoomEntities.UserEntity

@Database(entities = [PostEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class PostDatabase: RoomDatabase() {

    abstract fun postsDao(): PostsDAO
    abstract fun usersDao(): UsersDAO

    //Singleton with context as parameter
    companion object {
        var instance: PostDatabase? = null

        fun getPostDataBase(context: Context): PostDatabase? {
            if (instance == null) {
                synchronized(PostDatabase::class) {
                    instance =
                        Room.databaseBuilder(context.applicationContext, PostDatabase::class.java, "PostDB").build()
                }
            }
            return instance
        }
    }
}