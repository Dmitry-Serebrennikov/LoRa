package com.example.sensorstracker.ui.engeneer.addsensor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    fun addSensor(code : Int, lat : Float, long : Float){

        /** WARNING: SHITTY CODE */

        val response = ResponseMessage("Successfully registered")
        thread(start = true) {
            Thread.sleep(abs(Random.nextLong()%5 * 1000))
            responseLiveData.postValue(response)
        }

        /** SHITTY CODE HAS ENDED */

        /*val disposable = repository.registerSensor(code, lat, long).observeOn(AndroidSchedulers.mainThread()).subscribe({
            responseLiveData.postValue(it.body())
            if (it.code() == 200){
                repository.addToMySensors(code)
            }
        },{})
        compositeDisposable.add(disposable)*/
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}