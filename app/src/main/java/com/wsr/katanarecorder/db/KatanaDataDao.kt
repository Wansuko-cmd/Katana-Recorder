package com.wsr.katanarecorder.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KatanaDataDao {
    @Query("SELECT * FROM katana_data_table")
    fun getAll(): LiveData<MutableList<KatanaData>>

    @Insert
    fun insert(katanaData: KatanaData)

    @Delete
    fun delete(katanaData: KatanaData)

    @Query("DELETE FROM katana_data_table")
    fun deleteAll()
}