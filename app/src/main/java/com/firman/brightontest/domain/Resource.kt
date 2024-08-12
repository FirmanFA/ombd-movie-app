package com.firman.brightontest.domain

sealed class Resource<out T> {
    data object Pending : Resource<Nothing>()
    data class Success<T>(val data: T? = null) : Resource<T>()
    data class Failure(val e: Throwable) : Resource<Nothing>()
}