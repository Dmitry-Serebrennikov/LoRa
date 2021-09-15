package com.example.sensorstracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SensorEntity::class], version = 1)
abstract class SensorDatabase : RoomDatabase(){
    abstract val sensorDAO : SensorDAO
}