package com.example.movieapp

sealed class DataHandler<out T> {
    class Success<out T>(val data: T) : DataHandler<T>()
    class Failure(val msg: Throwable) : DataHandler<Nothing>()
    object Loading : DataHandler<Nothing>()
}