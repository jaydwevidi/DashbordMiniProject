package com.example.dashbordminiproject.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RFBuilder {

    var baseURL = "http://senajobapi.sigmasoftwares.org/Service/"

    var builder = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val placeHolderAPI: JsonGetter = builder.create(JsonGetter::class.java)
}
