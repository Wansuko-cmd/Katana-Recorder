package com.wsr.katanarecorder.ui.detail

import com.airbnb.epoxy.TypedEpoxyController
import com.wsr.katanarecorder.ListShowCell2BindingModel_
import com.wsr.katanarecorder.ListShowCell3BindingModel_
import com.wsr.katanarecorder.ListShowCell4BindingModelBuilder
import com.wsr.katanarecorder.ListShowCell4BindingModel_
import com.wsr.katanarecorder.db.SampleModel

class DetailEpoxyController : TypedEpoxyController<SampleModel>() {
    override fun buildModels(data: SampleModel) {
        //val list: List<String> = listOf("刃文", "地鉄", "帽子", "茎", "備考")

        val list = mapOf(
            "産地" to data.from,
            "時代" to data.age,
            "刃長" to data.length,
            "反り" to data.warp,
            "刃文" to data.hamon,
            "地鉄" to data.zigane,
            "帽子" to data.edge,
            "茎" to data.grip,
            "備考" to data.remarks
        )

        val pare = listOf<setPare>(setPare(data.from, data.age), setPare(data.length, data.warp))

        ListShowCell2BindingModel_()
            .title(data.title)
            .kind(data.kind)
            .id(modelCountBuiltSoFar)
            .addTo(this)

        list.forEach{
            ListShowCell4BindingModel_()
                .item(it.key)
                .content(it.value)
                .id(modelCountBuiltSoFar)
                .addTo(this)
        }


        /*pare.forEach{
            ListShowCell3BindingModel_()
                .item1("テスト")
                .contents1(it.left)
                .item2("テスト")
                .contents2(it.right)
                .id(modelCountBuiltSoFar)
                .addTo(this)
        }*/


        /*ListShowCell4BindingModel_()
            .content(data)*/
    }
}

data class setPare(
    val left: String,
    val right: String
)