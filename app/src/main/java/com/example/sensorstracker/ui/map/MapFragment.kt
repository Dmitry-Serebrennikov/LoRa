package com.example.sensorstracker.ui.map

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.sensorstracker.R
import com.example.sensorstracker.ui.IOnToolbarNavClick
import com.example.sensorstracker.ui.engeneer.addsensor.AddSensorEngineerFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory

import android.graphics.Bitmap
import android.graphics.Canvas

import androidx.core.content.ContextCompat

import android.graphics.drawable.Drawable
import android.widget.Toast
import com.example.sensorstracker.ui.mechanic.qrscan.QrScanViewModel
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.SupportMapFragment

import com.google.android.gms.maps.model.BitmapDescriptor
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MapFragment(val role: Role) : Fragment(), OnMapReadyCallback{
    val viewModel : MapViewModel by viewModel()
    val qrViewModel : QrScanViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    lateinit var toolbar : Toolbar
    lateinit var frameBottomSheet : FrameLayout
    lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    lateinit var bottomContainer : FragmentContainerView
    lateinit var addSensorsButton : Button
    lateinit var googleMap : GoogleMap
    lateinit var mapView: MapView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (role){
            Role.Engineer -> view.findViewById<TextView>(R.id.toolbar_title).text = "Engineer"
            Role.Mechanic -> view.findViewById<TextView>(R.id.toolbar_title).text = "Mechanic"
        }
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.leftarrow)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        mapView = view.findViewById(R.id.map_fragment)
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        mapView.getMapAsync(this)

        addSensorsButton = view.findViewById(R.id.add_sensor_button)
        frameBottomSheet = view.findViewById(R.id.bottomSheetBehaviour)
        bottomSheetBehavior = BottomSheetBehavior.from(frameBottomSheet)
        bottomContainer = view.findViewById(R.id.bottomContainer)

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        showAddSensorsBottomSheet()

        toolbar.children.forEach {
            (it as? AppCompatImageButton)?.imageTintList =
                ColorStateList.valueOf(Color.WHITE)
            it.refreshDrawableState()
        }
        when (role){
            Role.Engineer -> addSensorsButton.setOnClickListener {
                showAddSensorsBottomSheet()
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            Role.Mechanic -> addSensorsButton.setOnClickListener {
                viewModel.goToQrScan()
            }
        }
        qrViewModel.responseLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it, Toast.LENGTH_SHORT).show()
        }
    }

    fun showAddSensorsBottomSheet(){
        val addSensorEngineerFragment = AddSensorEngineerFragment(object : IOnToolbarNavClick{
            override fun onToolbarNavClick() {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }
        })
        val ft = activity?.supportFragmentManager?.beginTransaction()
        ft?.replace(R.id.bottomContainer, addSensorEngineerFragment)
        ft?.commit()
    }

    enum class Role{
        Engineer, Mechanic
    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0

        viewModel.savedSensors.observe(viewLifecycleOwner){
            googleMap.clear()
            for (sensor in it){
                googleMap.addMarker(MarkerOptions().title(sensor.code.toString()).position(
                    LatLng(sensor.lat.toDouble(), sensor.long.toDouble())
                ))
            }
        }
    }


}