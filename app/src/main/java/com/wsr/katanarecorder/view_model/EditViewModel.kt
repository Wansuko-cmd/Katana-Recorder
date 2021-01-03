package com.wsr.katanarecorder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.wsr.katanarecorder.db.SampleModel

class EditViewModel(application: Application) : AndroidViewModel(application){
    private var status: SampleModel = SampleModel(0, "", mutableMapOf())

    fun setStatus(value: SampleModel){
        this.status = value
    }

    fun setValue(key: String, value: String){
        if(status.value[key] != null) status.value[key] = value
        else status.value[key] = value
    }

    fun getStatus(): SampleModel{
        return status
    }
}