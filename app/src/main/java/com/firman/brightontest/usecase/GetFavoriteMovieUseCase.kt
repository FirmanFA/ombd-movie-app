package com.firman.brightontest.usecase

import com.firman.brightontest.data.model.GetMovieResponse
import com.firman.brightontest.domain.Resource
import com.firman.brightontest.domain.usecase.GetFavoriteMovie
import com.firman.brightontest.repository.FavoriteMovieFlow
import com.firman.brightontest.repository.main.MainRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

//class GetFavoriteMovieUseCase(private val mainRepository: MainRepository) :GetFavoriteMovie() {
////    override fun execute(params: String?): Resource<MutableList<GetMovieResponse.Search>> {
////        return mainRepository.getFavoriteMovie()
////    }
//
//
//}