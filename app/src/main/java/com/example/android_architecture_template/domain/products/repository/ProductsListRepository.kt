package com.example.android_architecture_template.domain.products.repository

import androidx.paging.PagingData
import com.example.android_architecture_template.domain.products.entity.Beer
import kotlinx.coroutines.flow.Flow

interface ProductsListRepository {
    fun getBeersList(ids: String): Flow<PagingData<Beer>>
}