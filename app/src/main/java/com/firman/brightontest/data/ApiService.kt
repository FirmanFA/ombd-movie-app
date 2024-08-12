package com.firman.brightontest.data

import com.firman.brightontest.data.model.GetDetailMovieResponse
import com.firman.brightontest.data.model.GetMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun getMovies(
        @Query("s") search: String = "batman",
        @Query("page") page: Int
    ): Response<GetMovieResponse>

    @GET("/")
    suspend fun getMovieDetail(
        @Query("i") id:String
    ): Response<GetDetailMovieResponse>

}