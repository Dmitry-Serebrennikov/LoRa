package com.example.sensorstracker.data.retrofit

import com.google.gson.annotations.SerializedName

data class SensorPOJO(
    @SerializedName("id") val id : Int,
    @SerializedName("code") val code : String,
    @SerializedName("latitude") val latitude : Float,
    @SerializedName("longitude") val longitude : Float,
    @SerializedName("freq") val freq : Float
)

data class MultipleSensorsPOJO(
    @SerializedName("data") val sensors : List<SensorPOJO>
)