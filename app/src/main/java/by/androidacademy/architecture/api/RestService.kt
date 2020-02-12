package by.androidacademy.architecture.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RestService {

    val api: MoviesApi

    init {
        api = createRetrofit()
            .create()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}