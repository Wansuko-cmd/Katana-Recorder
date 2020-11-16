package com.wsr.katanarecorder.ui.detail.edit

import com.airbnb.epoxy.TypedEpoxyController
import com.wsr.katanarecorder.ListEditCell2BindingModel_
import com.wsr.katanarecorder.ListEditCell3BindingModel_
import com.wsr.katanarecorder.view_model.EditViewModel

class DetailEditEpoxyController : TypedEpoxyController<EditViewModel>() {

    override fun buildModels(editViewModel: EditViewModel) {
        val list = editViewModel.getAsMap()

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
                .id(it.key)
                .addTo(this)
        }
    }
}