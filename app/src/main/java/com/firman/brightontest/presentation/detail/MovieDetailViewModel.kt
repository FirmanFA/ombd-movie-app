package com.firman.brightontest.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firman.brightontest.data.model.GetDetailMovieResponse
import com.firman.brightontest.domain.Resource
import com.firman.brightontest.domain.usecase.GetMovieDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailViewModel(private val getMovieDetail: GetMovieDetail) : ViewModel() {
    private val _movieDetailState =
        MutableStateFlow<Resource<Response<GetDetailMovieResponse>>>(Resource.Pending)
    val movieDetailState get() = _movieDetailState

    fun fetchMovieDetail(movieId: String) {
        viewModelScope.launch {
            getMovieDetail(movieId).collect { resource ->
                _movieDetailState.value = resource
            }
        }

    }

}