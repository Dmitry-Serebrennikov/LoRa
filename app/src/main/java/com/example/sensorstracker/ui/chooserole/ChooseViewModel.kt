package com.example.sensorstracker.ui.chooserole

import androidx.lifecycle.ViewModel
import com.example.sensorstracker.Screens
import com.github.terrakok.cicerone.Router

class ChooseViewModel(
    val router : Router

    ) : ViewModel() {

    fun chooseEngineer(){
        router.navigateTo(Screens.EngineerMapScreen())
    }

    fun chooseMechanic(){
        router.navigateTo(Screens.MechanicMapScreen())
    }

}