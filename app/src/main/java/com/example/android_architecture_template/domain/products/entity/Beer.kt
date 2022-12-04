package com.example.android_architecture_template.domain.products.entity

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String?,
    val abv: Double,
)