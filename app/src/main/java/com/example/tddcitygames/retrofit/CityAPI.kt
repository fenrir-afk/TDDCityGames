package com.example.tddcitygames.retrofit

import com.example.tddcitygames.model.City
import retrofit2.Call
import retrofit2.http.GET

interface CityAPI {

    @GET("city.list.json")
    fun getCityList(): Call<List<City>>
}