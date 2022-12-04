package com.example.android_architecture_template.data.network

data class ErrorBody(
    val statusCode: Int,
    val error: String,
    val message: String,
)