package com.example.sensorstracker.data.retrofit

import com.google.gson.annotations.SerializedName

data class ResponseMessage(
    @SerializedName("msg") val message: String
)
