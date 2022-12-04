package com.example.android_architecture_template.domain.products.usecase

import androidx.paging.PagingData
import com.example.android_architecture_template.domain.products.entity.Beer
import kotlinx.coroutines.flow.Flow

fun interface GetBeersListUseCase : (String) -> Flow<PagingData<Beer>>