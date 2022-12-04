package com.example.android_architecture_template.data.products.remote

import com.example.android_architecture_template.data.network.BaseApiService
import com.example.android_architecture_template.data.products.entity.BeerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi : BaseApiService {

    @GET("beers")
    suspend fun getBeersList(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 40,
    ): List<BeerResponse>
}