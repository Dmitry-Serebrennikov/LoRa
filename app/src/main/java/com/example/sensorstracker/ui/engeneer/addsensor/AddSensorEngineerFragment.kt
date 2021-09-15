package com.example.sensorstracker.ui.engeneer.addsensor

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import com.example.sensorstracker.R
import com.example.sensorstracker.data.model.Sensor
import com.example.sensorstracker.ui.IOnToolbarNavClick
import com.example.sensorstracker.ui.map.MapViewModel
import com.google.android.gms.maps.MapView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddSensorEngineerFragment(var onToolbarNavClick: IOnToolbarNavClick) : BottomSheetDialogFragment() {
    val viewModel : AddSensorEngineerViewModel by viewModel()
    val mapViewModel : MapViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_sensor_engineer, container, false)
    }

    lateinit var toolbar : Toolbar
    lateinit var addSensorButton: Button
    lateinit var periodEditText: EditText
    lateinit var freqEditText: EditText
    lateinit var nameEditText: EditText


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.toolbar_title).text = "Add sensor"
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.leftarrow)
        toolbar.setNavigationOnClickListener {
            onToolbarNavClick.onToolbarNavClick()
        }

        periodEditText = view.findViewById(R.id.sensor_period_et)
        nameEditText = view.findViewById(R.id.sensor_name_et)
        freqEditText = view.findViewById(R.id.sensor_freq_et)
        addSensorButton = view.findViewById(R.id.add_sensor_button)

        addSensorButton.setOnClickListener {
            if (nameEditText.text.toString().isNotEmpty() && periodEditText.text.toString()
                    .isNotEmpty() && periodEditText.text.toString().isNotEmpty()
            ) {
                viewModel.addSensor(
                    nameEditText.text.toString().toInt(),
                    periodEditText.text.toString().toFloat(),
                    freqEditText.text.toString().toFloat()
                )
                mapViewModel.addSensor(
                    Sensor(
                        nameEditText.text.toString().toInt(),
                        periodEditText.text.toString().toFloat(),
                        freqEditText.text.toString().toFloat()
                )
                )
                onToolbarNavClick.onToolbarNavClick()
            }
        }

        viewModel.responseLiveData.observe(viewLifecycleOwner){
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT ).show()
        }

        toolbar.children.forEach {
            (it as? AppCompatImageButton)?.imageTintList =
                ColorStateList.valueOf(Color.WHITE)
            it.refreshDrawableState()
        }


    }

}