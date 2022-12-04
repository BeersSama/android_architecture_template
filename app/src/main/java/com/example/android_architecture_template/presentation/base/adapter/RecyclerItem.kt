package com.example.android_architecture_template.presentation.base.adapter

interface RecyclerItem {
    val id: Int?
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}