package com.example.android_architecture_template.data.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseRetrofit @Inject constructor(
    okHttpClient: OkHttpClient,
    gson: Gson,
) {
    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }

    val retrofit: Retrofit = Retrofit.Builder()
        // BuildConfig.BASE_URL
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

}