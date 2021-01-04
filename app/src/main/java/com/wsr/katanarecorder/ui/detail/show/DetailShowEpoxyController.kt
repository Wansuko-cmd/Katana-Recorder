package com.wsr.katanarecorder.ui.detail.show

import com.airbnb.epoxy.TypedEpoxyController
import com.wsr.katanarecorder.ListShowCell2BindingModel_
import com.wsr.katanarecorder.ListShowCell3BindingModel_
import com.wsr.katanarecorder.db.SampleModel

//詳細画面のUIを出すところ
class DetailShowEpoxyController : TypedEpoxyController<SampleModel>() {
    override fun buildModels(data: SampleModel?) {
        if(data == null) return
        val status = data.value

        ListShowCell2BindingModel_()
            .title(data.title)
            .id(modelCountBuiltSoFar)
            .addTo(this)

        status.forEach{
            ListShowCell3BindingModel_()
                .item(it.key)
                .content(it.value)
                .id(modelCountBuiltSoFar)
                .addTo(this)
        }
    }
}