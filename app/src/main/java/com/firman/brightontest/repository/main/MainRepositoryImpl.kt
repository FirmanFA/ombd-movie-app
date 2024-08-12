package com.firman.brightontest.repository.main

import com.firman.brightontest.data.LocalDataSource
import com.firman.brightontest.data.RemoteDataSource
import com.firman.brightontest.data.model.GetMovieResponse
import com.firman.brightontest.repository.FavoriteMovieFlow
import com.firman.brightontest.repository.MovieDetailFlow
import com.firman.brightontest.repository.MoviesFlow

class MainRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
//    private val localDataSource: LocalDataSource
) : MainRepository {
    override fun getMovies(page: Int): MoviesFlow = remoteDataSource.getMovies(page = page)
    override fun getMovieDetail(id: String): MovieDetailFlow = remoteDataSource.getMovieDetail(id)
//    override fun getFavoriteMovie(): FavoriteMovieFlow =
//        localDataSource.getFavoriteMovie()

//    override fun setFavorite(data: GetMovieResponse.Search): MutableList<GetMovieResponse.Search>? =
//        localDataSource.setFavorite(data)

}