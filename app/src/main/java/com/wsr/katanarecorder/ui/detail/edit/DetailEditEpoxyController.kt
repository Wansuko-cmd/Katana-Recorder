package com.wsr.katanarecorder.ui.detail.edit

import android.app.Activity
import com.airbnb.epoxy.Typed2EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.wsr.katanarecorder.ListEditCell1BindingModel_
import com.wsr.katanarecorder.ListEditCell2BindingModel_
import com.wsr.katanarecorder.ListEditCell3BindingModel_
import com.wsr.katanarecorder.R
import com.wsr.katanarecorder.view_model.EditViewModel

class DetailEditEpoxyController : Typed2EpoxyController<Activity, EditViewModel>() {

    override fun buildModels(activity: Activity, editViewModel: EditViewModel) {
        val list = editViewModel.getAsMap()

        ListEditCell1BindingModel_()
            .src(R.drawable.ic_add_a_photo)
            .cameraController(DetailEditCameraController(activity))
            .id(modelCountBuiltSoFar)
            .addTo(this)

        ListEditCell2BindingModel_()
            .title(list["銘"])
            .watcher(DetailEditTextWatcher("銘", editViewModel))
            .id(modelCountBuiltSoFar)
            .addTo(this)

        list.forEach {
            ListEditCell3BindingModel_()
                .item(it.key)
                .content(it.value)
                .watcher(DetailEditTextWatcher(it.key, editViewModel))
                .id(modelCountBuiltSoFar)
                .addTo(this)
        }
    }
}