package com.example.sensorstracker.data.retrofit

import com.google.gson.annotations.SerializedName

data class AddSensorBody(
    @SerializedName("code") val code : Int,
    @SerializedName("lat") val lat : Float,
    @SerializedName("long") val long : Float
)