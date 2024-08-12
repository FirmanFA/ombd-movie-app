package com.firman.brightontest.routes

sealed class Routes(
    val route: String
)  {

    object MainScreen: Routes(route = "main-screen")

    object FavoriteScreen: Routes(route = "favorite-screen")

    object MovieDetailScreen: Routes(route = "movie-detail/{movieId}") {
        fun createRoute(movieId: String) = "movie-detail/$movieId"
    }

}