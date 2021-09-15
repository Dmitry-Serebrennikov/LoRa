package com.example.sensorstracker.di

import com.example.sensorstracker.ui.chooserole.ChooseViewModel
import com.example.sensorstracker.ui.engeneer.map.EngineerMapViewModel
import com.example.sensorstracker.ui.mechanic.map.MechanicMapViewModel
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
    viewModel { EngineerMapViewModel(get()) }
    viewModel { MechanicMapViewModel(get()) }

}

val dataModule = module {

}
