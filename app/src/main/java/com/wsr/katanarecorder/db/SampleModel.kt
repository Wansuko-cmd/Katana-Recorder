package com.wsr.katanarecorder.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class SampleModel(
    val id: Int,
    val title: String
)

class SampleDB{
    private val test1 = SampleModel(0, "title1")
    private val test2 = SampleModel(1, "title2")
    private val test3 = SampleModel(2, "title3")

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
