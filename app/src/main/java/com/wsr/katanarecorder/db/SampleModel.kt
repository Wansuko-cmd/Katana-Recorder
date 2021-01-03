package com.wsr.katanarecorder.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/*
id:シングルトンのid
title:銘
kind:メモする刀の種類
from:産地
age:時代
length:刃長
warp:反り
hamon:刃文
zigane:地鉄
edge:帽子
grip:茎
remarks:備考
 */

data class SampleModel(
    val id: Int,
    val title: String,
    val value: MutableMap<String, String>
)

class SampleDB{
    private val test1 = SampleModel(0, "備前長船盛光",
        mutableMapOf(
            "銘" to "備前長船盛光",
            "種別" to "太刀",
            "産地" to "備前長船派",
            "時代" to "室町中期",
            "刃長" to "７９ｃｍ",
            "反り" to "２．４ｃｍ",
            "刃文" to "丁子",
            "地鉄" to "柾目",
            "帽子" to "小丸返る",
            "茎" to "生ぶ",
            "備考" to "こちらは備前長船刀剣博物館にて貯蔵されている一振り。室町前期の太刀でありながら生ぶであり、大変貴重なものとなっている。"
        )
    )

    private val data: MutableLiveData<MutableList<SampleModel>> by lazy{
        MutableLiveData<MutableList<SampleModel>>()
    }

    init{
        data.value = mutableListOf(test1)
    }

    fun getAll(): LiveData<MutableList<SampleModel>>{
        return data
    }

    companion object{
        @Volatile
        private var INSTANCE: SampleDB? = null

        fun getDatabase(): SampleDB{
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance
            synchronized(this){
                val instance: SampleDB = SampleDB()
                INSTANCE = instance
                return instance
            }
        }
    }
}
