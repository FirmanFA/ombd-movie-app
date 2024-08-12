package com.firman.brightontest.di

import com.firman.brightontest.presentation.detail.MovieDetailViewModel
import com.firman.brightontest.presentation.main.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.get
import org.koin.dsl.module

val viewModels = module {
    viewModel {
        MainViewModel(
            get(named("GetMovies"))
        )
    }
    viewModel {
        MovieDetailViewModel(
            get(named("GetMovieDetail"))
        )
    }
}