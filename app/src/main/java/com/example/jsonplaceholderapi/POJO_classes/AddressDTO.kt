package com.example.jsonplaceholderapi.POJO_classes

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class AddressDTO(

    @field:SerializedName("zipcode")
	val zipcode: String? = null,

    @field:SerializedName("geo")
	val geo: GeoDTO? = null,

    @field:SerializedName("suite")
	val suite: String? = null,

    @field:SerializedName("city")
	val city: String? = null,

    @field:SerializedName("street")
	val street: String? = null
)