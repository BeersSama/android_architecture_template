package com.example.android_architecture_template.data.products.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android_architecture_template.data.products.datasource.ProductsPagingSource
import com.example.android_architecture_template.domain.extension.allowReads
import com.example.android_architecture_template.domain.products.entity.Beer
import com.example.android_architecture_template.domain.products.repository.ProductsListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsListRepositoryImpl @Inject constructor(
    private val pagingSource: ProductsPagingSource
) : ProductsListRepository {

    override fun getBeersList(ids: String): Flow<PagingData<Beer>> =
        allowReads {
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false,
                    maxSize = 30,
                    prefetchDistance = 5,
                    initialLoadSize = 10,
                    jumpThreshold = 60
                ),
                pagingSourceFactory = { pagingSource }
            ).flow
        }
}