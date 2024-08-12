package com.firman.brightontest.di

import com.firman.brightontest.data.ApiService
import com.firman.brightontest.data.RemoteDataSource
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://www.omdbapi.com/"
val api = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor {
                var request = it.request()
                val url: HttpUrl =
                    request.url.newBuilder().addQueryParameter("apiKey", "9e02555e").build()
                request = request.newBuilder().url(url).build()
                it.proceed(request)

            }
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    singleOf(::RemoteDataSource)

}