package com.example.sensorstracker.data.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Body

import retrofit2.http.POST

interface ConnectionAPI {
    @GET("/data/{device_code}")
    fun getDeviceById(@Path("device_code") id : Int) : Single<SensorPOJO>

    @GET("/check/{device_code}")
    fun checkPos(@Path("device_code") device_code: String?): Single<CoordsPOJO>

    @POST("/register/{device_code}")
    fun sendPos(
        @Path("device_code") device_code: String,
        @Body coords: CoordsPOJO
    ): Single<String>
}