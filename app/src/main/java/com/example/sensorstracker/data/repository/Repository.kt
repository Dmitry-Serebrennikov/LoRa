package com.example.sensorstracker.data.repository

import android.util.Log
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

    fun registerSensor(code : Int, lat : Float, long : Float) : Single<Response<ResponseMessage>>{
        Log.d("SensorReg", "$code $lat $long")
        return networkService.getConnectionAPI().sendPos(AddSensorBody(code, lat, long)).subscribeOn(Schedulers.io())
    }

    fun addToMySensors(sensor : Sensor){
        val sensorEntity = SensorEntity(sensor.code, sensor.lat, sensor.long)
        database.sensorDAO.insertAll(sensorEntity).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe()
    }

    fun deleteFromMySensors(sensor : Sensor){
        val sensorEntity = SensorEntity(sensor.code, sensor.lat, sensor.long)
        database.sensorDAO.delete(sensorEntity).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe()
    }

    fun getSensors() : Single<List<Sensor>> {
        return networkService.getConnectionAPI().getAllDevices().map {
            val sensorList : MutableList<Sensor> = mutableListOf()
            for (i in it.sensors){
                sensorList.add(Sensor(i.code.toInt(), i.latitude, i.longitude))
            }
            sensorList.toList()
        }.subscribeOn(Schedulers.io())
    }

    fun createSensor(type : String, freq : Float) : Single<SensorCreateResponse>{
        return networkService.getConnectionAPI().createSensor(
            createSensorBody(type, freq)
        ).subscribeOn(Schedulers.io())
    }
}