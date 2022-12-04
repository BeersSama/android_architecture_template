package com.example.android_architecture_template.presentation.products.entity

import androidx.recyclerview.widget.RecyclerView
import com.example.android_architecture_template.databinding.ItemProductBinding
import com.example.android_architecture_template.presentation.extension.load

class BeerViewHolder(val itemBinding: ItemProductBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(beer: BeerUI) = with(itemView) {
        itemBinding.itemProductContainer.transitionName = beer.id.toString()
        itemBinding.itemProductIdTxv.text = beer.id.toString()
        itemBinding.itemProductImv.load(beer.imageUrl)
        itemBinding.itemProductNameTxv.text = beer.name
        itemBinding.itemProductAbvTxv.text = beer.abv.toString()
    }
}