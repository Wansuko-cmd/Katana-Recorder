package com.wsr.katanarecorder.repository

import androidx.lifecycle.LiveData
import com.wsr.katanarecorder.db.KatanaData
import com.wsr.katanarecorder.db.KatanaDataDao
import com.wsr.katanarecorder.db.SampleDB
import com.wsr.katanarecorder.db.SampleModel

//リポジトリ
class Repository(private val katanaDataDao: KatanaDataDao) {
    val katanaData: LiveData<MutableList<KatanaData>> = katanaDataDao.getAll()

    suspend fun insert(katanaData: KatanaData){
        katanaDataDao.insert(katanaData)
    }

    suspend fun update(katanaData: KatanaData){
        katanaDataDao.update(katanaData)
    }

    suspend fun delete(katanaData: KatanaData){
        katanaDataDao.delete(katanaData)
    }
}