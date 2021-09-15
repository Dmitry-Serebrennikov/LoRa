package com.example.sensorstracker

import com.example.sensorstracker.ui.chooserole.ChooseFragment
import com.example.sensorstracker.ui.map.MapFragment
import com.example.sensorstracker.ui.mechanic.qrscan.QrFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun ChooseScreen() =  FragmentScreen{ ChooseFragment() }
    fun EngineerMapScreen() = FragmentScreen{ MapFragment(MapFragment.Role.Engineer) }
    fun MechanicMapScreen() = FragmentScreen{ MapFragment(MapFragment.Role.Mechanic) }
    fun QRScanScreen() = FragmentScreen{ QrFragment() }
}