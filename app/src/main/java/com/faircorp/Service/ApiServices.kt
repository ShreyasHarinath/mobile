package com.faircorp.Service

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {

    val API_USERNAME = "admin"
    val API_PASSWORD = "password"

    val windowsApiService : WindowApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://faircorp-shreyasharinath.cleverapps.io/api/")
            .build()
            .create(WindowApiService::class.java)
    }

    val heatersApiService : HeaterApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://faircorp-shreyasharinath.cleverapps.io/api/")
            .build()
            .create(HeaterApiService::class.java)
    }

    val roomsApiService : RoomApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://faircorp-shreyasharinath.cleverapps.io/api/")
            .build()
            .create(RoomApiService::class.java)
    }
}