package com.wsr.katanarecorder.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/*
id:シングルトンのid
title:銘
value:刀ごとの詳細を記述
*/

data class SampleModel(
    val id: Int,
    val title: String,
    val value: MutableMap<String, String>
)
