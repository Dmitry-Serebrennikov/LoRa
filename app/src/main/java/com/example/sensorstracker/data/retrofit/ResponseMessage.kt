package com.example.sensorstracker.data.retrofit

import com.google.gson.annotations.SerializedName

data class ResponseMessage(
    @SerializedName("msg") val message: String
)

data class SensorCreateResponse(
    @SerializedName("msg") val message: String,
    @SerializedName("data") val sensor : SensorPOJO
)