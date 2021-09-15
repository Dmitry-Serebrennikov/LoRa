package com.example.sensorstracker.ciceronenav

import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.androidx.AppNavigator

open class NormalNavigator(activity: FragmentActivity, containerId: Int) : AppNavigator(
    activity,
    containerId
) {
    fun hasScreensInBackStack() : Boolean{
        return localStackCopy.size > 1
    }
}