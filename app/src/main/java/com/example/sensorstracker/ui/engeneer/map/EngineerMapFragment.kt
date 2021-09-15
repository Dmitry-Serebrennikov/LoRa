package com.example.sensorstracker.ui.engeneer.map

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.sensorstracker.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class EngineerMapFragment : Fragment(){
    val viewModel : EngineerMapViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_engineer_map, container, false)
    }

    lateinit var toolbar : Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.toolbar_title).text = "Engineer"
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.leftarrow)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        toolbar.children.forEach {
            (it as? AppCompatImageButton)?.imageTintList =
                ColorStateList.valueOf(Color.WHITE)
            it.refreshDrawableState()
        }
    }
}