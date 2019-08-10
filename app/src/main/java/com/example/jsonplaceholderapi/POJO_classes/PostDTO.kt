package com.example.jsonplaceholderapi.POJO_classes

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class PostDTO(

    @field:SerializedName("userId")
    val userId: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("body")
    val body: String? = null
)