package com.wsr.katanarecorder.permission

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat

//画像の許可を取るためのもの。SAFを利用することになったので将来的に消す
class RequestPermission(private val activity: Activity) {
    private val REQUEST_PERMISSION: Int = 10


    fun setPermission(): Boolean{
        return if(Build.VERSION.SDK_INT >= 23){
            Log.d("MainActivity", "SDK > 23")
            checkPermission()
        } else{
            Log.d("MainActivity", "SDK < 22")
            true
        }
    }

    //Permissionの確認
    private fun checkPermission(): Boolean{
        //既に許可している
        return if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) ==
            PackageManager.PERMISSION_GRANTED){
            true
        }
        // 拒否していた場合
        else{
            requestLocationPermission()
            false
        }
    }

    //許可を求める
    private fun requestLocationPermission(){
        if(activity.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            activity.requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION)
        }else{
            Toast.makeText(activity, "許可されないとこの機能は利用できません", Toast.LENGTH_SHORT).show()
            activity.requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION)
        }
    }
}