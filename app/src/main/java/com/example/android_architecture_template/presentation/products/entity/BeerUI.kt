package com.example.android_architecture_template.presentation.products.entity

import android.os.Parcelable
import com.example.android_architecture_template.domain.products.entity.Beer
import com.example.android_architecture_template.presentation.base.adapter.RecyclerItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeerUI(
    override val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String?,
    val abv: Double,
) : RecyclerItem, Parcelable

fun Beer.mapIt(): BeerUI =
    BeerUI(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        imageUrl = imageUrl,
        abv = abv
    )