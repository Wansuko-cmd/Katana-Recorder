package com.wsr.katanarecorder.ui.detail.edit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult

class DetailEditCameraController(private val activity: Activity, ) {
    private val RESULT_PICK_IMAGEFILE = 1001

    fun setOnClickListener(){



        val intent: Intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply{
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(activity, intent, RESULT_PICK_IMAGEFILE, Bundle());

        Toast.makeText(activity, "成功", Toast.LENGTH_SHORT).show()
        /*if(RequestPermission(activity).setPermission()){
            Toast.makeText(activity, "成功", Toast.LENGTH_SHORT).show()
        }*/
    }
}