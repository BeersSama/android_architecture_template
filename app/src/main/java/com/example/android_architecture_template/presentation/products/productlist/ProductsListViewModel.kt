package com.example.android_architecture_template.presentation.products.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.android_architecture_template.domain.products.entity.Beer
import com.example.android_architecture_template.domain.products.usecase.GetBeersListUseCase
import com.example.android_architecture_template.presentation.base.adapter.RecyclerItem
import com.example.android_architecture_template.presentation.base.viewmodel.BaseViewModel
import com.example.android_architecture_template.presentation.products.choose.CHOOSE_PATH_TYPE
import com.example.android_architecture_template.presentation.products.choose.ChoosePathType
import com.example.android_architecture_template.presentation.products.entity.mapIt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersListUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _ldProductsList: MutableLiveData<PagingData<RecyclerItem>> = MutableLiveData()
    val ldProductsList: LiveData<PagingData<RecyclerItem>> = _ldProductsList

    private val _productsListByCoroutine =
        MutableStateFlow<PagingData<RecyclerItem>>(PagingData.empty())
    val productsListByCoroutine: Flow<PagingData<RecyclerItem>> = _productsListByCoroutine


    init {
        val path =
            savedStateHandle.get<ChoosePathType>(CHOOSE_PATH_TYPE) ?: ChoosePathType.COROUTINE
        Timber.d("Which path: $path")
        getProductsBaseOnPath("", path)
    }

    private fun getProductsByCoroutinePath(ids: String) =
        getBeersUseCase(ids)
            .cachedIn(viewModelScope)

    private fun getProductsBaseOnPath(ids: String, path: ChoosePathType) {
        when (path) {
            ChoosePathType.COROUTINE -> {
                viewModelScope.launch {
                    _productsListByCoroutine.value = getProductsByCoroutinePath(ids).first()
                        .map(Beer::mapIt)
                }
            }
            else -> {}
        }
    }

}