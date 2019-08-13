package com.example.jsonplaceholderapi.LocalData.RoomDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jsonplaceholderapi.LocalData.RoomEntities.UserEntity

@Dao
interface UsersDAO {

    @Query("SELECT * from users_table ORDER BY id ASC")
    fun getAllUsersFromRoom(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Query("DELETE FROM users_table")
    fun deleteAll()

}