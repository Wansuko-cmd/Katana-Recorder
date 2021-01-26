package com.wsr.katanarecorder.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KatanaDataDao {
    @Query("SELECT * FROM katana_data_table")
    fun getAll(): List<KatanaData>

    @Insert
    fun insert(katanaData: KatanaData)

    @Delete
    fun delete(katanaData: KatanaData)

    @Query("DELETE FROM katana_data_table")
    fun deleteAll()
}