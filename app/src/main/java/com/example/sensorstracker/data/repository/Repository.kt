package com.example.sensorstracker.data.repository

import com.example.sensorstracker.data.database.SensorDatabase
import com.example.sensorstracker.data.database.SensorEntity
import com.example.sensorstracker.data.retrofit.CoordsPOJO
import com.example.sensorstracker.data.retrofit.NetworkService
import com.example.sensorstracker.data.retrofit.ResponseMessage
import com.example.sensorstracker.data.retrofit.SensorPOJO
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class Repository(val networkService: NetworkService, val database: SensorDatabase) {
    fun searchSensorById(id : Int) : Single<SensorPOJO>{
        return networkService.getConnectionAPI().getDeviceById(id).subscribeOn(Schedulers.io())
    }

    fun registerSensor(code : Int, lat : Float, long : Float) : Single<Response<ResponseMessage>>{
        return networkService.getConnectionAPI().sendPos(code, lat, long).subscribeOn(Schedulers.io())
    }

    fun addToMySensors(id : Int){
        val sensorEntity = SensorEntity(id)
        database.sensorDAO.insertAll(sensorEntity).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe()
    }

    fun deleteFromMySensors(id : Int){
        val sensorEntity = SensorEntity(id)
        database.sensorDAO.delete(sensorEntity).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe()
    }

    fun getFavouritePokemonIds() : Single<List<SensorEntity>> {
        return database.sensorDAO.getAll().subscribeOn(Schedulers.io())
    }

}