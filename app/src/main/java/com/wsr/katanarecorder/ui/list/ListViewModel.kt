package com.wsr.katanarecorder.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.wsr.katanarecorder.db.SampleModel
import com.wsr.katanarecorder.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application){
    private val repository: Repository = Repository()

    fun getAll(): List<SampleModel> {
        return repository.getAll()
    }
}