package com.wsr.katanarecorder.ui.detail.edit

import android.app.Activity
import com.airbnb.epoxy.Typed2EpoxyController
import com.airbnb.epoxy.Typed3EpoxyController
import com.wsr.katanarecorder.ListEditCell1BindingModel_
import com.wsr.katanarecorder.ListEditCell2BindingModel_
import com.wsr.katanarecorder.ListEditCell3BindingModel_
import com.wsr.katanarecorder.ListEditCell4BindingModel_
import com.wsr.katanarecorder.view_model.EditViewModel

//編集画面用のEpoxyController
class DetailEditEpoxyController : Typed3EpoxyController<Activity, EditViewModel, DetailEditAlertDialog>() {

    override fun buildModels(
        activity: Activity,
        editViewModel: EditViewModel,
        detailEditAlertDialog: DetailEditAlertDialog
    ) {
        val status = editViewModel.getStatus()

        //画像
        ListEditCell1BindingModel_()
            .detailEditImageSetter(editViewModel.getDetailEditImageSetter())
            .id(modelCountBuiltSoFar)
            .addTo(this)

        //銘
        ListEditCell2BindingModel_()
            .title(status.title)
            .watcher(DetailEditTextWatcher("title", editViewModel, 2))
            .id(modelCountBuiltSoFar)
            .addTo(this)

        //詳細
        status.value.forEach {
            ListEditCell3BindingModel_()
                .item(it.key)
                .content(it.value)
                .watcher(DetailEditTextWatcher(it.key, editViewModel, 3))
                .id(modelCountBuiltSoFar)
                .addTo(this)
        }

        ListEditCell4BindingModel_()
            .detailEditAlertDialog(detailEditAlertDialog)
            .id(modelCountBuiltSoFar)
            .addTo(this)

    }
}