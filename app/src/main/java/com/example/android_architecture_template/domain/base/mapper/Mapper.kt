package com.example.android_architecture_template.domain.base.mapper

interface Mapper<in LeftObject, out RightObject> {
    fun mapLeftToRight(obj: LeftObject): RightObject
}