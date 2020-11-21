package com.wsr.katanarecorder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.wsr.katanarecorder.db.SampleModel

class EditViewModel(application: Application) : AndroidViewModel(application){
    private var list =  mutableMapOf(
        "銘" to "",
        "種別" to "",
        "産地" to "",
        "時代" to "",
        "刃長" to "",
        "反り" to "",
        "刃文" to "",
        "地鉄" to "",
        "帽子" to "",
        "茎" to "",
        "備考" to ""
    )

    fun setValue(key: String, value: String){
        list[key] = value
    }

    fun getAsMap(): Map<String, String>{
        return list
    }
}