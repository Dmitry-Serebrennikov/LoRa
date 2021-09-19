package com.example.sensorstracker.di

import androidx.room.Room
import com.example.sensorstracker.data.database.SensorDatabase
import com.example.sensorstracker.data.repository.Repository
import com.example.sensorstracker.data.retrofit.NetworkService
import com.example.sensorstracker.ui.chooserole.ChooseViewModel
import com.example.sensorstracker.ui.engeneer.addsensor.AddSensorEngineerViewModel
import com.example.sensorstracker.ui.map.MapViewModel
import com.example.sensorstracker.ui.mechanic.qrscan.QrScanViewModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val navigationModule = module {
    single { Cicerone.create(Router()) }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
}

val viewModelsModule = module {

    viewModel { ChooseViewModel(get()) }
    viewModel { MapViewModel(get(), get()) }
    viewModel { AddSensorEngineerViewModel(get()) }
    viewModel { QrScanViewModel(get(), get()) }

}

val dataModule = module {
    single { NetworkService() }
    single { Repository(get(), get()) }
    single { Room.databaseBuilder(get(), SensorDatabase::class.java, "SensorDatabase").build() }
    single { get<SensorDatabase>().sensorDAO }
}