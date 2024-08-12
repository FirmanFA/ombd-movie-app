package com.firman.brightontest.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlin.coroutines.CoroutineContext

//abstract class LocalUseCase<in Params, out Type>(private val context: CoroutineContext = Dispatchers.IO) {
//
//    abstract fun execute(params: Params?): Resource<Type>
//
//    operator fun invoke(params: Params?) =
//        execute(params)
//            .onStart { emit(Resource.Pending) }
//            .catch { emit(Resource.Failure(it)) }
//            .flowOn(context)
//
//}