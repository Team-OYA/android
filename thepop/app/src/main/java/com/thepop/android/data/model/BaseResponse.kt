package com.thepop.android.data.model

data class BaseResponse<T> (
    val code: Int,
    val message: String,
    val data: T?
)