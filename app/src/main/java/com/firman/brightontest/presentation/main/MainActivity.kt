package com.firman.brightontest.presentation.main

import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.firman.brightontest.data.model.GetMovieResponse
import com.firman.brightontest.domain.Resource
import com.firman.brightontest.presentation.NavGraph
import com.firman.brightontest.presentation.main.ui.theme.BrightonTestTheme
import com.firman.brightontest.presentation.main.ui.theme.Typography
import com.firman.brightontest.routes.Routes
import org.koin.androidx.compose.koinViewModel
import org.koin.dsl.koinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrightonTestTheme {
                NavGraph(startDestination = "main-screen")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel
) {

    val postState = viewModel.postsState.collectAsState()
    val isRefreshing = postState.value is Resource.Pending
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    val pullToRefreshState =
        rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {
            viewModel.getMovies()
        })

    Column {

    }

    when (postState.value) {
        is Resource.Success -> {

            val moviesResult =
                (postState.value as Resource.Success).data?.body()?.search ?: emptyList()

            val filteredMovie = moviesResult.filter {
                it.title.lowercase().contains(searchText.lowercase())
            }

            Column {
                TextField(
                    value = searchText,
                    onValueChange = viewModel::onSearchTextChange,
                    label = { Text("Cari Film...") },
                    modifier = Modifier.fillMaxWidth()
                )
                Box(Modifier.pullRefresh(pullToRefreshState)) {
                    PullRefreshIndicator(
                        refreshing = isRefreshing,
                        state = pullToRefreshState,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                    LazyColumn(
                        modifier = modifier
                    ) {
                        items(filteredMovie) { movieData ->
                            MovieItem(movieData = movieData, modifier = Modifier.clickable {

                                navController.navigate(Routes.MovieDetailScreen.createRoute(movieData.imdbID))
                            })
                        }
                    }
                }
//                Button(onClick = {
//
//                }) {
//                    Text(text = "Lihat Favorit")
//                }
            }

        }

        is Resource.Failure -> {

            val msg = (postState.value as Resource.Failure).e.localizedMessage

            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Gagal $msg!",
                    modifier = modifier
                )
            }
        }

        is Resource.Pending -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }


}

@Composable
fun MovieItem(movieData: GetMovieResponse.Search, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Row(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .weight(3f)
                    .fillMaxWidth(),
            ) {
                AsyncImage(
                    model = movieData.poster,
                    contentDescription = "Movie Poster",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(7f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movieData.title,
                    style = Typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Text(
                    text = "${movieData.type} - ${movieData.year}",
                    style = Typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BrightonTestTheme {

    }
}