package com.example.sensorstracker.data.database

import androidx.room.*
import io.reactivex.Single

@Dao
interface SensorDAO {
    @Query("SELECT * FROM sensorentity")
    fun getAll() : Single<List<SensorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sensor: SensorEntity) : Single<Long>

    @Delete
    fun delete(sensor: SensorEntity) : Single<Int>

    @Query("SELECT count(*)!=0 FROM sensorentity WHERE id = :id")
    fun containsId(id: Int): Single<Boolean>
}