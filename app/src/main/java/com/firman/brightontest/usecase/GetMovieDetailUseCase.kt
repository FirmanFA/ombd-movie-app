package com.firman.brightontest.usecase

import com.firman.brightontest.data.model.GetDetailMovieResponse
import com.firman.brightontest.domain.Resource
import com.firman.brightontest.domain.usecase.GetMovieDetail
import com.firman.brightontest.repository.main.MainRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class GetMovieDetailUseCase(private val mainRepository: MainRepository) :GetMovieDetail() {
    override fun execute(params: String?): Flow<Resource<Response<GetDetailMovieResponse>>> =
        mainRepository.getMovieDetail(id = params ?: "")


}