package com.wsr.katanarecorder.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface KatanaDataDao {
    @Query("SELECT * FROM katana_data_table")
    fun getAll(): LiveData<MutableList<KatanaData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(katanaData: KatanaData)

    @Update
    fun update(katanaData: KatanaData)

    @Delete
    fun delete(katanaData: KatanaData)

    @Query("DELETE FROM katana_data_table")
    fun deleteAll()
}