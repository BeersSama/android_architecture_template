package com.example.android_architecture_template.presentation.products.choose

import java.io.Serializable

const val CHOOSE_PATH_TYPE = "choosePathType"

enum class ChoosePathType : Serializable {
    COROUTINE,
    RX
}