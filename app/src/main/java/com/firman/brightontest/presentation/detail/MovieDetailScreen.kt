package com.firman.brightontest.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.firman.brightontest.data.model.GetDetailMovieResponse
import com.firman.brightontest.domain.Resource
import com.firman.brightontest.presentation.main.MovieItem
import com.firman.brightontest.routes.Routes
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieDetailScreen(
    movieId: String,
    viewModel: MovieDetailViewModel,
    navController: NavController
) {

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetail(movieId)
    }


    val movieDetailState = viewModel.movieDetailState.collectAsState()
    val isRefreshing = movieDetailState.value is Resource.Pending

    val pullToRefreshState =
        rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {
            viewModel.fetchMovieDetail(movieId)
        })

    Box(Modifier.pullRefresh(pullToRefreshState))

    when (movieDetailState.value) {
        is Resource.Success -> {

            val movieData =
                (movieDetailState.value as Resource.Success).data?.body() ?: GetDetailMovieResponse()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = movieData.poster,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = movieData.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Release Date: ${movieData.year}",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Rating: ${movieData.imdbRating}",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = movieData.plot,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }


        }

        is Resource.Failure -> {

            val msg = (movieDetailState.value as Resource.Failure).e.localizedMessage

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.material3.Text(
                    text = "Gagal $msg!",
                )
            }
        }

        is Resource.Pending -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }


}