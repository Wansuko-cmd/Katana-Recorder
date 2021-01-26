package com.wsr.katanarecorder.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.wsr.katanarecorder.db.*
import com.wsr.katanarecorder.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//詳細を表示するために使うViewModel
class ListViewModel(application: Application) : AndroidViewModel(application){
    private val repository: Repository
    val katanaData: LiveData<MutableList<KatanaData>>
    init{
        val katanaDataDao: KatanaDataDao = KatanaDatabase.getDatabase(application, viewModelScope).katanaDataDao()
        repository = Repository(katanaDataDao)
        katanaData = repository.katanaData
    }

    fun insert(katanaData: KatanaData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(katanaData)
    }

    fun update(katanaData: KatanaData) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(katanaData)
    }

    fun delete(katanaData: KatanaData) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(katanaData)
    }
}