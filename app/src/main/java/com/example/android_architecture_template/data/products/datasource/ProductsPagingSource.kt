package com.example.android_architecture_template.data.products.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android_architecture_template.data.products.entity.BeerResponse
import com.example.android_architecture_template.data.products.entity.mapIt
import com.example.android_architecture_template.data.products.remote.ProductsApi
import com.example.android_architecture_template.domain.base.Failure
import com.example.android_architecture_template.domain.products.entity.Beer
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.inject.Singleton

private const val STARTING_PAGE_INDEX = 1

@Singleton
class ProductsPagingSource @Inject constructor(
    private val productsApi: ProductsApi,
    //private val query: String
) : PagingSource<Int, Beer>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        val position = params.key ?: STARTING_PAGE_INDEX
        //val apiQuery = query

        return try {
            val response = productsApi.getBeersList(position)
                .map(BeerResponse::mapIt)

            toLoadResult(response, position)
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException, is SocketTimeoutException -> {
                    LoadResult.Error(
                        Failure.NoInternet(e.message)
                    )
                }
                is TimeoutException -> {
                    LoadResult.Error(
                        Failure.Timeout(e.message)
                    )
                }
                else -> {
                    LoadResult.Error(
                        Failure.Unknown(e.message)
                    )
                }
            }
        }
    }

    override val jumpingSupported = true

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? = state.anchorPosition

    private fun toLoadResult(
        response: List<Beer>,
        position: Int,
    ): LoadResult<Int, Beer> {
        return LoadResult.Page(
            data = response,
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (response.isEmpty()) null else position + 1,
            itemsBefore = LoadResult.Page.COUNT_UNDEFINED,
            itemsAfter = LoadResult.Page.COUNT_UNDEFINED
        )
    }
}