package com.wsr.katanarecorder.view_model

import android.app.Activity
import android.app.Application
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.wsr.katanarecorder.db.SampleDB
import com.wsr.katanarecorder.db.SampleModel
import com.wsr.katanarecorder.ui.detail.edit.DetailEditEpoxyController
import com.wsr.katanarecorder.ui.detail.edit.DetailEditFragment
import com.wsr.katanarecorder.ui.detail.edit.DetailEditImageSetter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.Exception
import java.util.*

//編集画面で、編集しているものを保存しておくところ
class EditViewModel(application: Application) : AndroidViewModel(application){

    private lateinit var detailEditImageSetter: DetailEditImageSetter
    private lateinit var activity: Activity

    private var status: SampleModel = SampleModel(0, "", null, mutableMapOf())
    private var uri: Uri? = null

    //全部の値を一括挿入するところ
    fun setStatus(value: SampleModel) {
        this.status = value
    }

    //個々の値を更新、もしくは挿入するところ
    fun setValue(key: String, value: String){
        if(status.value[key] != null) status.value[key] = value
        else status.value[key] = value
        Log.i("セットする値", "key: ${key}, value: $value")
    }

    //画像をセットするための関数を代入するところ
    fun setDetailEditImageSetter(instance: DetailEditImageSetter){
        this.detailEditImageSetter = instance
    }

    //外部からファイルを取得したときのみ使用
    fun setUrl(uri: Uri){
        this.uri = uri
        Log.i("選択された画像のurl", uri.toString())
    }

    //Activityをセットするところ
    fun setActivity(activity: Activity){
        this.activity = activity
    }

    fun addValue(key: String){
        this.status.value[key] = ""
    }

    //ここで管理している値を全部返り値に持つ関数
    fun getStatus(): SampleModel{
        return status
    }

    //画像をセットするための関数を返り値に持つ関数
    fun getDetailEditImageSetter(): DetailEditImageSetter{
        return detailEditImageSetter
    }

    //表示する画像のinputStream型を返す関数
    fun getInputStream(): InputStream?{
        return when {
            this.uri != null -> {
                try{
                    activity.contentResolver?.openInputStream(this.uri!!)
                }catch(e: Exception) {
                    null
                }
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

    //変更を保存するための関数
    fun save(){

        //画像の保存
        val filename = UUID.randomUUID().toString() + ".jpg"
        val path = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File(path, filename)

        if(isExternalStorageWritable()){
            val output = FileOutputStream(file)

            val buf = ByteArray(DEFAULT_BUFFER_SIZE)

            getInputStream()?.let{ inputStream ->
                var len: Int
                while(inputStream.read(buf).let{len = it; it != -1}){
                    output.write(buf, 0, len)
                }
                output.flush()

                Log.i("セーブした画像のURL", file.toString())
            }

            //もともと設定してあった画像の削除
            status.url?.let{
                val deleteFile = File(path, it)
                deleteFile.delete()
            }

            //新しい画像名を設定
            status.url = filename
        }

        //詳細の保存
        val instance = SampleDB.getDatabase()
        instance.save(status)

        Log.i("セーブしたid", status.id.toString())
    }

    //書けるかどうかを確認するための関数
    private fun isExternalStorageWritable(): Boolean{
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state
    }
}