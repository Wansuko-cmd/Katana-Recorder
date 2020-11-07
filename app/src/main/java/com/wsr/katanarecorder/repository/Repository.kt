package com.wsr.katanarecorder.repository

import androidx.lifecycle.LiveData
import com.wsr.katanarecorder.db.SampleDB
import com.wsr.katanarecorder.db.SampleModel

class Repository {
    private val list: LiveData<MutableList<SampleModel>> = SampleDB.getDatabase().getAll()
}