package com.example.sensorstracker.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sensorstracker.Screens
import com.example.sensorstracker.data.model.Sensor
import com.example.sensorstracker.data.repository.Repository
import com.example.sensorstracker.data.retrofit.SensorPOJO
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlin.concurrent.thread

class MapViewModel(
    val router: Router, val repository: Repository
) : ViewModel() {

    val scannersLiveData : MutableLiveData<Sensor> = MutableLiveData()
    val savedSensors : MutableLiveData<List<Sensor>> = MutableLiveData()

    fun addSensor(sensor : Sensor){
        scannersLiveData.postValue(sensor)
    }

    fun goToQrScan(){
        router.navigateTo(Screens.QRScanScreen())
    }

    init {
        getSavedSensors()
    }

   //val compositeDisposable : CompositeDisposable = CompositeDisposable()

    fun getSavedSensors(){
        val disposable = repository.getSensors().observeOn(AndroidSchedulers.mainThread()).subscribe({
            savedSensors.postValue(it)
        },{})
    /*

        thread {
            while (true){
                val disposable = repository.getSensors().observeOn(AndroidSchedulers.mainThread()).subscribe({
                    savedSensors.postValue(it)
                },{})

                //compositeDisposable.add(disposable)
                Thread.sleep(2000)
            }
        }.start()

         */
    }
}