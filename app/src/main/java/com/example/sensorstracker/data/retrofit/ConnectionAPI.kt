package com.example.sensorstracker.data.retrofit

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Body

import retrofit2.http.POST

interface ConnectionAPI {
    @GET("api/sensor/sensors/")
    fun getAllDevices() : Single<MultipleSensorsPOJO>

    @POST("api/sensor/sensors/create/")
    fun createSensor(
        @Body createSensorBody: createSensorBody
    ) : Single<SensorCreateResponse>


    @POST("api/sensor/register/")
    fun sendPos(
        @Body body : AddSensorBody
        //@Body code : Int, @Body lat : Float, @Body  long: Float
    ): Single<Response<ResponseMessage>>
}