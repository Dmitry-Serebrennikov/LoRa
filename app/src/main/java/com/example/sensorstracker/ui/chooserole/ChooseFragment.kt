package com.example.sensorstracker.ui.chooserole

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.view.get
import com.example.sensorstracker.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseFragment : Fragment() {

    val viewModel : ChooseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose, container, false)
    }

    lateinit var chooseRoleSpinner : Spinner
    lateinit var chooseRoleButton: Button


    val rolesList : MutableList<String> = mutableListOf("Engineer", "Mechanic")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chooseRoleSpinner = view.findViewById(R.id.choose_role_spinner)
        val adapter = ArrayAdapter<String>(activity?.applicationContext!!, android.R.layout.simple_spinner_item, rolesList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        chooseRoleSpinner.adapter = adapter

        chooseRoleButton = view.findViewById(R.id.choose_role_button)
        chooseRoleButton.setOnClickListener {
            when (chooseRoleSpinner.selectedItemPosition) {
                0 -> viewModel.chooseEngineer()
                1 -> viewModel.chooseMechanic()
            }
        }



    }
}