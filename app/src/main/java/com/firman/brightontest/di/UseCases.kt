package com.firman.brightontest.di

import com.firman.brightontest.domain.usecase.GetFavoriteMovie
import com.firman.brightontest.domain.usecase.GetMovieDetail
import com.firman.brightontest.domain.usecase.GetMovies
//import com.firman.brightontest.usecase.GetFavoriteMovieUseCase
import com.firman.brightontest.usecase.GetMovieDetailUseCase
import com.firman.brightontest.usecase.GetMoviesUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCases = module {
    single<GetMovies>(named("GetMovies")) { GetMoviesUseCase(get()) }
    single<GetMovieDetail>(named("GetMovieDetail")) { GetMovieDetailUseCase(get()) }
//    single<GetFavoriteMovie>(named("GetFavoriteMovie")) { GetFavoriteMovieUseCase(get()) }
}