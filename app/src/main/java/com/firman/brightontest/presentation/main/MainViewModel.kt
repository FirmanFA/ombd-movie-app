package com.firman.brightontest.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firman.brightontest.data.model.GetMovieResponse
import com.firman.brightontest.domain.Resource
import com.firman.brightontest.domain.usecase.GetMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlinx.coroutines.flow.*

class MainViewModel(private val getMovies: GetMovies): ViewModel() {

    private val _moviesState =
        MutableStateFlow<Resource<Response<GetMovieResponse>>>(Resource.Pending)
    val postsState get() = _moviesState

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onToggleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }

    init {
        getMovies()
    }

    fun getMovies() {

        viewModelScope.launch {
            getMovies(1).onStart {
                _moviesState.value = Resource.Pending
            }.onEach {
                _moviesState.value = it
            }.catch {
                _moviesState.value = Resource.Failure(it)
            }.launchIn(viewModelScope)
        }
    }

}