package com.example.android_architecture_template.di.module

import com.example.android_architecture_template.domain.products.repository.ProductsListRepository
import com.example.android_architecture_template.domain.products.usecase.GetBeersListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    @ViewModelScoped
    fun getBeersListUseCase(
        productsListRepository: ProductsListRepository,
    ): GetBeersListUseCase =
        GetBeersListUseCase(productsListRepository::getBeersList)

}