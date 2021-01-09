package com.wsr.katanarecorder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.wsr.katanarecorder.db.SampleDB
import com.wsr.katanarecorder.db.SampleModel
import com.wsr.katanarecorder.repository.Repository

//詳細を表示するために使うViewModel
class ListViewModel(application: Application) : AndroidViewModel(application){
    private val repository: Repository = Repository()
    val list: LiveData<MutableList<SampleModel>> = SampleDB.getDatabase().getAll()
}