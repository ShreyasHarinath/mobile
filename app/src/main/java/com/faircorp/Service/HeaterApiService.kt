package com.faircorp.Service

import com.faircorp.model.HeaterDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface HeaterApiService {
    @GET("heater")
    fun findAll(): Call<List<HeaterDto>>

    @GET("heater/{id}")
    fun findById(@Path("id") id: Long): Call<HeaterDto>
}