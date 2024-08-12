package com.firman.brightontest.data

import com.firman.brightontest.domain.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class RemoteDataSource(
    private val apiService: ApiService
) {

    fun getMovies(page: Int) = flow {
        while (true) {
            val posts = apiService.getMovies(page = page)
            emit(Resource.Success(posts))
            delay(10000)
        }
    }

    fun getMovieDetail(id:String) = flow {
        val posts = apiService.getMovieDetail(id)
        emit(Resource.Success(posts))
    }

}