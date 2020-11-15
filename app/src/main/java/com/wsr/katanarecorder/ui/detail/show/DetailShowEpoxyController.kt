package com.wsr.katanarecorder.ui.detail.show

import com.airbnb.epoxy.TypedEpoxyController
import com.wsr.katanarecorder.ListShowCell2BindingModel_
import com.wsr.katanarecorder.ListShowCell3BindingModel_
import com.wsr.katanarecorder.db.SampleModel

class DetailShowEpoxyController : TypedEpoxyController<SampleModel>() {
    override fun buildModels(data: SampleModel?) {
        if(data == null) return
        val list = mapOf(
            "種類" to data.kind,
            "産地" to data.from,
            "時代" to data.age,
            "刃長" to data.length,
            "反り" to data.warp,
            "刃文" to data.hamon,
            "地鉄" to data.zigane,
            "鋩子" to data.edge,
            "茎" to data.grip,
            "備考" to data.remarks
        )

        ListShowCell2BindingModel_()
            .title(data.title)
            .id(modelCountBuiltSoFar)
            .addTo(this)

        list.forEach{
            ListShowCell3BindingModel_()
                .item(it.key)
                .content(it.value)
                .id(modelCountBuiltSoFar)
                .addTo(this)
        }
    }
}