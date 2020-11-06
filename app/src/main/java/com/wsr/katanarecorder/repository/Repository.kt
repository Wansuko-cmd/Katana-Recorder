package com.wsr.katanarecorder.repository

import com.wsr.katanarecorder.db.SampleDB
import com.wsr.katanarecorder.db.SampleModel

class Repository {
    private val SampleDB = SampleDB()

    fun getAll(): List<SampleModel>{
        return SampleDB.getData()
    }
}