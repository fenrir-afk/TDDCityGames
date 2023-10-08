package com.example.tddcitygames.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val cityApi:CityAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/lkozyr/CityList/master/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityAPI::class.java)
    }
}