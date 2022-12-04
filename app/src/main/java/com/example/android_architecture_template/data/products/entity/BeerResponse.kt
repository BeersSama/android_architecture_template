package com.example.android_architecture_template.data.products.entity

import com.example.android_architecture_template.domain.products.entity.Beer
import com.google.gson.annotations.SerializedName

data class BeerResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("abv") val abv: Double,
)

fun BeerResponse.mapIt(): Beer =
    Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        imageUrl = imageUrl,
        abv = abv
    )