package com.firman.brightontest.domain.usecase

import com.firman.brightontest.data.model.GetDetailMovieResponse
import com.firman.brightontest.data.model.GetMovieResponse
import com.firman.brightontest.domain.UseCase
import retrofit2.Response

typealias GetMovies = UseCase<Int, Response<GetMovieResponse>>

typealias GetMovieDetail = UseCase<String, Response<GetDetailMovieResponse>>

typealias GetFavoriteMovie = UseCase<String, MutableList<GetMovieResponse.Search>>

