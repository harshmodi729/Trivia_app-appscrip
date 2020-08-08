package com.example.triviaapp.base

sealed class BaseResult<out T : Any> {
    class Success<out T : Any>(val item: T) : BaseResult<T>()
    class Error(val exception: Exception, val errorMessage: String = exception.localizedMessage!!) :
        BaseResult<Nothing>()
}