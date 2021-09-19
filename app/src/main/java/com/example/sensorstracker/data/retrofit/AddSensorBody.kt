package com.example.sensorstracker.data.retrofit

import com.google.gson.annotations.SerializedName

data class AddSensorBody(
    @SerializedName("code") val code : Int, //Int
    @SerializedName("latitude") val lat : Float,
    @SerializedName("longitude") val long : Float
)