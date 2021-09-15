package com.example.sensorstracker

import com.example.sensorstracker.ui.chooserole.ChooseFragment
import com.example.sensorstracker.ui.engeneer.map.EngineerMapFragment
import com.example.sensorstracker.ui.mechanic.map.MechanicMapFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun ChooseScreen() =  FragmentScreen{ ChooseFragment() }
    fun EngineerMapScreen() = FragmentScreen{ EngineerMapFragment() }
    fun MechanicMapScreen() = FragmentScreen{ MechanicMapFragment() }
}