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

    val responseLiveData : MutableLiveData<String> = MutableLiveData()

    val compositeDisposable = CompositeDisposable()
    fun addSensor(freq : Float, type : String){

        val disposable = repository.createSensor(type, freq).observeOn(AndroidSchedulers.mainThread()).subscribe({
            responseLiveData.postValue(it.message)
        },{})
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}

