package com.wsr.katanarecorder.ui.detail

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.wsr.katanarecorder.R
import com.wsr.katanarecorder.ui.detail.show.DetailShowFragmentArgs
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity: AppCompatActivity() {
    val REQUEST_PERMISSION = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //表示するIDの取得
        val id = intent.getIntExtra("ID", -1)
        val args = DetailShowFragmentArgs(id)

        //toolbarの設定
        detail_toolbar.inflateMenu(R.menu.detail_show_menu)

        //FragmentにIDを渡す
        val navController = supportFragmentManager
            .findFragmentById(R.id.nav_detail_fragment)!!
            .findNavController()
        navController.setGraph(R.navigation.detail_navigation, args.toBundle())
    }

    /*
    //Permissionの確認
    private fun checkPermission(){
        //既に許可している
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) ==
            PackageManager.PERMISSION_GRANTED){
            readContentActivity()
        }
        // 拒否していた場合
        else{
            requestLocationPermission()
        }
    }

    //許可を求める
    private fun requestLocationPermission(){
        if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION)
        }else{
            Toast.makeText(this, "許可されないとこの機能は利用できません", Toast.LENGTH_SHORT).show()
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION)
        }
    }

    private fun readContentActivity(){

    }

    private fun isExternalStorageReadable(): Boolean{
        val state: String = Environment.getExternalStorageState()
        return (Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_PERMISSION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
        }
    }*/
}