package com.wsr.katanarecorder.ui.detail.edit

import android.app.Activity
import com.airbnb.epoxy.Typed2EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.wsr.katanarecorder.ListEditCell1BindingModel_
import com.wsr.katanarecorder.ListEditCell2BindingModel_
import com.wsr.katanarecorder.ListEditCell3BindingModel_
import com.wsr.katanarecorder.R
import com.wsr.katanarecorder.view_model.EditViewModel

//編集画面用のEpoxyController
class DetailEditEpoxyController : Typed2EpoxyController<Activity, EditViewModel>() {

    override fun buildModels(activity: Activity, editViewModel: EditViewModel) {
        val status = editViewModel.getStatus()

        //画像
        ListEditCell1BindingModel_()
            .detailEditImageSetter(editViewModel.getDetailEditImageSetter())
            .id(modelCountBuiltSoFar)
            .addTo(this)

        //銘
        ListEditCell2BindingModel_()
            .title(status.title)
            .watcher(DetailEditTextWatcher("銘", editViewModel))
            .id(modelCountBuiltSoFar)
            .addTo(this)

        //詳細
        status.value.forEach {
            ListEditCell3BindingModel_()
                .item(it.key)
                .content(it.value)
                .watcher(DetailEditTextWatcher(it.key, editViewModel))
                .id(modelCountBuiltSoFar)
                .addTo(this)
        }
    }
}