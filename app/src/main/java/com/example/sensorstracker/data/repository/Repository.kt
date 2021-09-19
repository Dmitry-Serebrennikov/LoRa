package com.example.sensorstracker.data.repository

import com.example.sensorstracker.data.database.SensorDatabase
import com.example.sensorstracker.data.database.SensorEntity
import com.example.sensorstracker.data.retrofit.*
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import com.example.sensorstracker.data.model.Sensor
import com.example.sensorstracker.data.retrofit.*
import io.reactivex.Completable.concat
import io.reactivex.Observable

class Repository(val networkService: NetworkService, val database: SensorDatabase) {
    fun searchSensorById(id : Int) : Single<SensorPOJO>{
        return networkService.getConnectionAPI().getDeviceById(id).subscribeOn(Schedulers.io())
    }

    fun registerSensor(code : Int, lat : Float, long : Float) : Single<Response<ResponseMessage>>{ // Int
        //return networkService.getConnectionAPI().sendPos(AddSensorBody(code, lat, long)).subscribeOn(Schedulers.io())
        return networkService.getConnectionAPI().sendPos(AddSensorBody(code, lat, long)).subscribeOn(Schedulers.io())
    }

    //fun addToMySensors(id : Int){
    //val sensorEntity = SensorEntity(id)
    fun addToMySensors(sensor : Sensor){
        val sensorEntity = SensorEntity(sensor.code, sensor.lat, sensor.long)
        database.sensorDAO.insertAll(sensorEntity).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe()
    }

    //fun deleteFromMySensors(id : Int){
    //val sensorEntity = SensorEntity(id)
    fun deleteFromMySensors(sensor : Sensor){
        val sensorEntity = SensorEntity(sensor.code, sensor.lat, sensor.long)
        database.sensorDAO.delete(sensorEntity).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe()
    }

    //fun getFavouritePokemonIds() : Single<List<SensorEntity>> {
    //return database.sensorDAO.getAll().subscribeOn(Schedulers.io())
    //}
    fun getSensors() : Single<List<Sensor>> {
        return database.sensorDAO.getAll().subscribeOn(Schedulers.io()).map {
            val sensorList : MutableList<Sensor> = mutableListOf()
            for (i in it){
                sensorList.add(Sensor(i.code, i.lat, i.longg))
            }
            sensorList
        }

    }}