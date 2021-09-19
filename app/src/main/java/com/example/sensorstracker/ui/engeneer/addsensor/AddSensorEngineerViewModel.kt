package com.example.sensorstracker.ui.engeneer.addsensor


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sensorstracker.data.model.Sensor
import com.example.sensorstracker.data.repository.Repository
import com.example.sensorstracker.data.retrofit.ResponseMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response
import kotlin.concurrent.thread
import kotlin.math.abs
import kotlin.random.Random

class AddSensorEngineerViewModel(val repository: Repository) : ViewModel(){

    val responseLiveData : MutableLiveData<ResponseMessage> = MutableLiveData()

    val compositeDisposable = CompositeDisposable()
    fun addSensor(code : Int, lat : Float, long : Float){ //Int

        repository.addToMySensors(Sensor(code, lat, long))

        val disposable = repository.registerSensor(code, lat, long).observeOn(AndroidSchedulers.mainThread()).subscribe({
            responseLiveData.postValue(it.body())
            Log.d("Response", it.toString())
            if (it.code() == 200){

            }
        },{})
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}

/*
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sensorstracker.data.model.Sensor
import com.example.sensorstracker.data.repository.Repository
import com.example.sensorstracker.data.retrofit.ResponseMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response
import java.io.BufferedReader
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import kotlin.concurrent.thread
import kotlin.math.abs
import kotlin.random.Random
class AddSensorEngineerViewModel(val repository: Repository) : ViewModel(){
    val responseLiveData : MutableLiveData<ResponseMessage> = MutableLiveData()
    val sensorLiveData : MutableLiveData<Sensor> = MutableLiveData()
    val compositeDisposable = CompositeDisposable()
    fun addSensor(code : Int, lat : Float, long : Float){
        val response = ResponseMessage("Successfully registered")
        //thread(start = true) {
            //Thread.sleep(abs(Random.nextLong()%5 * 1000))
            //responseLiveData.postValue(response)
        //}
        Log.d("ResponseCode", "TryToADD")
        val disposable = repository.registerSensor(code, lat, long).observeOn(AndroidSchedulers.mainThread()).subscribe({
            responseLiveData.postValue(it.body())
            Log.d("ResponseCode", it.code().toString() + " " + it.message().toString())
            if (it.code() == 200){
                repository.addToMySensors(code)
                sensorLiveData.postValue(Sensor(code, lat, long))
            }
        },{})
        compositeDisposable.add(disposable)
    }
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }*/