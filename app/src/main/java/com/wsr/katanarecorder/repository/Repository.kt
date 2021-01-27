package com.wsr.katanarecorder.repository

import androidx.lifecycle.LiveData
import com.wsr.katanarecorder.db.KatanaData
import com.wsr.katanarecorder.db.KatanaDataDao

//リポジトリ
class Repository(private val katanaDataDao: KatanaDataDao) {
    val katanaData: LiveData<MutableList<KatanaData>> = katanaDataDao.getAll()

    fun insert(katanaData: KatanaData): Int{
        return katanaDataDao.insert(katanaData).toInt()
    }

    fun update(katanaData: KatanaData){
        katanaDataDao.update(katanaData)
    }

    fun delete(katanaData: KatanaData){
        katanaDataDao.delete(katanaData)
    }
}