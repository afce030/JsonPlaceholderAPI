package com.example.jsonplaceholderapi.LocalData.RoomDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jsonplaceholderapi.LocalData.RoomEntities.FavoritesEntity
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity

@Dao
interface FavoritesDAO {

    @Query("SELECT * from favorites_table ORDER BY id ASC")
    fun getAllFavoritesFromRoom(): LiveData<List<FavoritesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoritesEntity: FavoritesEntity)

    @Query("DELETE FROM favorites_table")
    fun deleteAll()

}