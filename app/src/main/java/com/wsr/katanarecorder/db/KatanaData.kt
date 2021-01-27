package com.wsr.katanarecorder.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "katana_data_table")
data class KatanaData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "url") var url: String?,
    @ColumnInfo(name = "value") val value: MutableMap<String, String>
)
