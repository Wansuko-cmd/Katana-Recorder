package com.wsr.katanarecorder.db

/*
id:シングルトンのid
title:銘
value:刀ごとの詳細を記述
*/

data class SampleModel(
    val id: Int,
    val title: String,
    var url: String?,
    val value: MutableMap<String, String>
)
