package com.example.sensorstracker.data.retrofit

import com.google.gson.annotations.SerializedName

data class SensorPOJO(
    val type : String,
    val param : Double,
    val latitude : Double,
    val longitude : Double,
    val id : Int,
    val name : String,
    val groupID : Int
)
