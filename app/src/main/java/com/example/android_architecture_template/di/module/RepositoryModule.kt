package com.example.android_architecture_template.di.module

import com.example.android_architecture_template.data.products.datasource.ProductsPagingSource
import com.example.android_architecture_template.data.products.repository.ProductsListRepositoryImpl
import com.example.android_architecture_template.domain.products.repository.ProductsListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun productsList(
        pagingSource: ProductsPagingSource
    ): ProductsListRepository =
        ProductsListRepositoryImpl(pagingSource)

}