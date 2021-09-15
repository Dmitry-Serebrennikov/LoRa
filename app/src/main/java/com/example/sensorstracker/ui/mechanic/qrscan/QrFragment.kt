package com.example.sensorstracker.ui.mechanic.qrscan

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.sensorstracker.R
import com.google.common.util.concurrent.ListenableFuture


class QrFragment : Fragment() {

    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qr, container, false)
    }
    lateinit var previewView : PreviewView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previewView = view.findViewById(R.id.previewView)
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireActivity())

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    cameraProviderFuture.addListener(Runnable {
                        val cameraProvider = cameraProviderFuture.get()
                        bindPreview(cameraProvider)
                    }, ContextCompat.getMainExecutor(requireActivity()))
                } else {

                }
            }


        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) -> {
                cameraProviderFuture.addListener(Runnable {
                    val cameraProvider = cameraProviderFuture.get()
                    bindPreview(cameraProvider)
                }, ContextCompat.getMainExecutor(requireActivity()))
            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA)
            }
        }

    }

    fun bindPreview(cameraProvider : ProcessCameraProvider) {
        var preview : Preview = Preview.Builder()
            .build()

        var cameraSelector : CameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        preview.setSurfaceProvider(previewView.getSurfaceProvider())

        var camera = cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview)
    }
}