package com.firman.brightontest.usecase

import com.firman.brightontest.data.model.GetMovieResponse
import com.firman.brightontest.domain.Resource
import com.firman.brightontest.domain.usecase.GetMovies
import com.firman.brightontest.repository.main.MainRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class GetMoviesUseCase(private val mainRepository: MainRepository) :GetMovies() {
    override fun execute(params: Int?): Flow<Resource<Response<GetMovieResponse>>> =
        mainRepository.getMovies(params ?: 1)


}