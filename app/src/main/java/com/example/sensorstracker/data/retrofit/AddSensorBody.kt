package com.example.sensorstracker.data.retrofit

import com.google.gson.annotations.SerializedName

data class AddSensorBody(
    @SerializedName("code") val code : Int,
    @SerializedName("latitude") val lat : Float,
    @SerializedName("longitude") val long : Float
)

data class createSensorBody(
    @SerializedName("type") val type : String,
    @SerializedName("freq") val freq : Float
)
