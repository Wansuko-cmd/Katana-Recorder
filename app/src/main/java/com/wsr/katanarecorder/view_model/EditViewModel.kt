package com.wsr.katanarecorder.view_model

import android.app.Application
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.AndroidViewModel
import com.wsr.katanarecorder.db.SampleDB
import com.wsr.katanarecorder.db.SampleModel
import com.wsr.katanarecorder.ui.detail.edit.DetailEditImageSetter

//編集画面で、編集しているものを保存しておくところ
class EditViewModel(application: Application) : AndroidViewModel(application){
    private var status: SampleModel = SampleModel(0, "", mutableMapOf())
    private lateinit var detailEditImageSetter: DetailEditImageSetter

    //全部の値を一括挿入するところ
    fun setStatus(value: SampleModel){
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

    //ここで管理している値を全部返り値に持つ関数
    fun getStatus(): SampleModel{
        return status
    }

    fun getDetailEditImageSetter(): DetailEditImageSetter{
        return detailEditImageSetter
    }

    fun save(){
        val instance = SampleDB.getDatabase()
        instance.save(status)
    }
}