package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CatApi {
    @GET("cats")
    fun getCatsByParameter(
        @QueryMap options: Map<String, String>,
        @Header("X-Api-Key") apiKey: String?
    ): Call<List<CatModel>>
}
