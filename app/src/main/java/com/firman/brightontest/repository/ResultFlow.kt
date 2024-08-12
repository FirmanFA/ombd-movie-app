package com.firman.brightontest.repository

import com.firman.brightontest.data.model.GetDetailMovieResponse
import com.firman.brightontest.data.model.GetMovieResponse
import com.firman.brightontest.domain.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


typealias MoviesFlow = Flow<Resource<Response<GetMovieResponse>>>

typealias MovieDetailFlow = Flow<Resource<Response<GetDetailMovieResponse>>>

typealias FavoriteMovieFlow = Resource<MutableList<GetMovieResponse.Search>>

