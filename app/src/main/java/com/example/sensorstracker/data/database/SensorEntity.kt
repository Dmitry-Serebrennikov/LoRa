package com.example.sensorstracker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SensorEntity (
    @PrimaryKey
    val code: Int,
    val lat : Float,
    val longg : Float,
    )