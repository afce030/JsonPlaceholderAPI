package com.example.jsonplaceholderapi.LocalData.RoomEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
class UserEntity (

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id_user: Int? = null,

    @ColumnInfo(name = "website")
    val website: String? = null,

    @ColumnInfo(name = "zipcode")
    val zipcode: String? = null,

    @ColumnInfo(name = "lng")
    val lng: String? = null,

    @ColumnInfo(name = "lat")
    val lat: String? = null,

    @ColumnInfo(name = "suite")
    val suite: String? = null,

    @ColumnInfo(name = "city")
    val city: String? = null,

    @ColumnInfo(name = "street")
    val street: String? = null,

    @ColumnInfo(name = "phone")
    val phone: String? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "bs")
    val bs: String? = null,

    @ColumnInfo(name = "catchPhrase")
    val catchPhrase: String? = null,

    @ColumnInfo(name = "companyName")
    val companyName: String? = null,

    @ColumnInfo(name = "email")
    val email: String? = null,

    @ColumnInfo(name = "username")
    val username: String? = null

)