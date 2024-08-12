package com.firman.brightontest.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firman.brightontest.presentation.detail.MovieDetailScreen
import com.firman.brightontest.presentation.favorite.FavoriteScreen
import com.firman.brightontest.presentation.main.MainScreen
import com.firman.brightontest.routes.Routes
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.padding(top = 40.dp)
        ) { innerPadding ->

        Column {
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Routes.MainScreen.route) {
                    MainScreen(navController = navController, viewModel = koinViewModel())
                }
                composable(Routes.FavoriteScreen.route) {
                    FavoriteScreen()
                }
                composable(Routes.MovieDetailScreen.route) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("movieId") ?: return@composable
                    MovieDetailScreen(
                        movieId = id,
                        navController = navController,
                        viewModel = koinViewModel()
                    )
                }

            }
        }

    }

}