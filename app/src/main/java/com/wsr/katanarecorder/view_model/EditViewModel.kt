package com.wsr.katanarecorder.view_model

import android.app.Activity
import android.app.Application
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.wsr.katanarecorder.db.SampleDB
import com.wsr.katanarecorder.db.SampleModel
import com.wsr.katanarecorder.ui.detail.edit.DetailEditImageSetter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.util.*

//編集画面で、編集しているものを保存しておくところ
class EditViewModel(application: Application) : AndroidViewModel(application){

    //将来的にはここはidでとってくるようにする
    private var status: SampleModel = SampleModel(0, "", null, mutableMapOf())
    private lateinit var detailEditImageSetter: DetailEditImageSetter
    private lateinit var activity: Activity

    private var url: Uri? = null

    //全部の値を一括挿入するところ
    fun setStatus(value: SampleModel) {
        this.status = value
    }

    //個々の値を更新、もしくは挿入するところ
    fun setValue(key: String, value: String){
        if(status.value[key] != null) status.value[key] = value
        else status.value[key] = value
    }

    fun setDetailEditImageSetter(instance: DetailEditImageSetter){
        this.detailEditImageSetter = instance
    }

    //外部からファイルを取得したときのみ使用
    fun setUrl(url: Uri){
        this.url = url
    }

    fun setActivity(activity: Activity){
        this.activity = activity
    }

    //ここで管理している値を全部返り値に持つ関数
    fun getStatus(): SampleModel{
        return status
    }

    fun getDetailEditImageSetter(): DetailEditImageSetter{
        return detailEditImageSetter
    }

    fun getInputStream(): InputStream?{
        return when {
            this.url != null -> {
                activity.contentResolver?.openInputStream(this.url!!)
            }
            status.url != null -> {
                val path = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val file = File(path, status.url!!)
                FileInputStream(file)
            }
            else -> {
                null
            }
        }
    }

    fun save(){
        val filename = UUID.randomUUID().toString() + ".jpg"
        val path = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File(path, filename)

        Log.d("セーブURL",file.toString())

        if(isExternalStorageWritable()){
            val inputStream = getInputStream()
            val output = FileOutputStream(file)

            val DEFAULT_BUFFER_SIZE = 10240 * 4
            val buf = ByteArray(DEFAULT_BUFFER_SIZE)

            if (inputStream != null) {
                var len: Int
                while(inputStream.read(buf).let{len = it; it != -1}){
                    output.write(buf, 0, len)
                    //len = inputStream!!.read(buf)
                }
                output.flush()
            }

            status.url = filename
        }

        val instance = SampleDB.getDatabase()
        instance.save(status)
    }

    private fun isExternalStorageWritable(): Boolean{
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state
    }
}