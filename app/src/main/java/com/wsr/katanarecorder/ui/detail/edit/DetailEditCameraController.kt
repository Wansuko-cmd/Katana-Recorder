package com.wsr.katanarecorder.ui.detail.edit

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import com.wsr.katanarecorder.permission.RequestPermission

class DetailEditCameraController(private val activity: Activity) {

    fun setOnClickListener(){
        if(RequestPermission(activity).setPermission()){
            Toast.makeText(activity, "成功", Toast.LENGTH_SHORT).show()
        }
    }
}