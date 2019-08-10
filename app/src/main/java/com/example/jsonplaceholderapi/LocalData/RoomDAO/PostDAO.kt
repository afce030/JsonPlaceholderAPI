package com.example.jsonplaceholderapi.LocalData.RoomDAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity

@Dao
interface PostsDAO {

    @Query("SELECT * from posts_table ORDER BY id ASC")
    fun getAllWords(): List<PostEntity>

    @Insert
    suspend fun insert(postEntity: PostEntity)

    @Query("DELETE FROM posts_table")
    fun deleteAll()

}