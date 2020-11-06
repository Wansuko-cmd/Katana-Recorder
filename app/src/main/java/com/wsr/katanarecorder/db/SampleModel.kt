package com.wsr.katanarecorder.db

data class SampleModel(
    val title: String
)

class SampleDB{
    private val test1 = SampleModel("title1")
    private val test2 = SampleModel("title2")
    private val test3 = SampleModel("title3")

    private val data: MutableList<SampleModel> = mutableListOf(test1, test2, test3)

    fun getData(): List<SampleModel>{
        return data
    }
}
