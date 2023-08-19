package com.grewal.rgflash

import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.grewal.rgflash.databinding.ActivityFlashBinding
import com.grewal.rgflash.databinding.CustomToastLayoutBinding

class FlashActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlashBinding
    private lateinit var cameraManager: CameraManager
    private var cameraId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        val hasFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

        if (!hasFlash) {
            // Disable the toggle button if the device doesn't have a camera flash
            binding.toggleButton.isEnabled = false
        } else {
            // Set up toggle button listener
            binding.toggleButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    turnOnFlashlight()
                } else {
                    turnOffFlashlight()
                }
            }
        }
    }

    private fun turnOnFlashlight() {
        try {
            if (cameraId == null) {
                val cameraIdList = cameraManager.cameraIdList
                for (id in cameraIdList) {
                    val characteristics = cameraManager.getCameraCharacteristics(id)
                    val flashAvailable = characteristics.get(CameraCharacteristics.LENS_FACING) ==
                            CameraCharacteristics.LENS_FACING_BACK &&
                            characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true

                    if (flashAvailable) {
                        cameraId = id
                        break
                    }
                }
            }

            if (cameraId != null) {
                cameraManager.setTorchMode(cameraId!!, true)
                showCustomToast("Flashlight is on...", Toast.LENGTH_SHORT)
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    private fun turnOffFlashlight() {
        try {
            if (cameraId != null) {
                cameraManager.setTorchMode(cameraId!!, false)
                showCustomToast("Flashlight is off...", 0)
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
    private fun showCustomToast(message: String, duration: Int) {
        val toastBinding = CustomToastLayoutBinding.inflate(layoutInflater)
        toastBinding.textViewToast.text = message

        val toastView = toastBinding.root
        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.duration = duration
        toast.view = toastView
        toast.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Turn off flashlight when the app is closed
        if (cameraId != null) {
            turnOffFlashlight()
        }
    }
}