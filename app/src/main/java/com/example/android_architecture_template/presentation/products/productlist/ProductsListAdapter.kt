package com.example.android_architecture_template.presentation.products.productlist

import android.view.View
import com.example.android_architecture_template.presentation.base.adapter.BasePagedListAdapter
import com.example.android_architecture_template.presentation.base.adapter.RecyclerItem
import com.example.android_architecture_template.presentation.products.entity.BeerCell

class ProductsListAdapter(onItemClick: (RecyclerItem, View) -> Unit) : BasePagedListAdapter(
    BeerCell,
    onItemClick = onItemClick
)