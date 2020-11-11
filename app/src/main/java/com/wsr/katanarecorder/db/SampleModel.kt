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
    val kind: String,
    val from: String,
    val age: String,
    val length: String,
    val warp: String,
    val hamon: String,
    val zigane: String,
    val edge: String,
    val grip: String,
    val remarks: String
)

class SampleDB{
    private val test1 = SampleModel(0,
        "備前１", "太刀", "備前青江派","室町前期", "７９ｃｍ",
        "２．４ｃｍ", "丁子", "柾目", "小丸返る", "生ぶ", "こちらは備前長船刀剣博物館にて貯蔵されている一振り。室町前期の太刀でありながら生ぶであり、大変貴重なものとなっている。")
    private val test2 = SampleModel(1,
        "備前２", "脇差", "備前長船","室町後期", "４４ｃｍ",
        "１．３ｃｍ", "互の目丁子", "杢目", "地蔵", "磨き上げ", "特になし")
    private val test3 = SampleModel(2,
        "備前３", "打ち刀", "小反り派","室町中期", "７２ｃｍ",
        "２．６ｃｍ", "直刃", "柾目", "火焔", "生ぶ", "特になし")

    private val data: MutableLiveData<MutableList<SampleModel>> by lazy{
        MutableLiveData<MutableList<SampleModel>>()
    }

    init{
        data.value = mutableListOf(test1, test2, test3)
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
