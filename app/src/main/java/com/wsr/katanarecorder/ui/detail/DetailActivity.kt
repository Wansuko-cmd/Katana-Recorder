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


}