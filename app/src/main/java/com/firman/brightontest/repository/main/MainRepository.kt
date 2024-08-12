package com.firman.brightontest.repository.main

import com.firman.brightontest.data.model.GetMovieResponse
import com.firman.brightontest.repository.FavoriteMovieFlow
import com.firman.brightontest.repository.MovieDetailFlow
import com.firman.brightontest.repository.MoviesFlow

interface MainRepository {

    fun getMovies(page: Int): MoviesFlow
    fun getMovieDetail(id:String): MovieDetailFlow
//    fun getFavoriteMovie(): FavoriteMovieFlow
//    fun setFavorite(data: GetMovieResponse.Search): MutableList<GetMovieResponse.Search>?

}