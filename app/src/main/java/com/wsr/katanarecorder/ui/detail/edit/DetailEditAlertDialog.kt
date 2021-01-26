package com.wsr.katanarecorder.ui.detail.edit

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import com.wsr.katanarecorder.view_model.EditViewModel

class DetailEditAlertDialog(
    private val activity: Activity,
    private val context: Context,
    private val editViewModel: EditViewModel,
    private val controller: DetailEditEpoxyController
    ) {

    fun addValue(){

        editViewModel.addValue("")
        controller.setData(activity, editViewModel, this)

        /*val editText = EditText(context)

        AlertDialog.Builder(activity)
            .setTitle("Test")
            .setMessage("TEST")
            .setView(editText)
            .setPositiveButton("OK") {_, _ ->
                editViewModel.addValue(editText.text.toString())
                controller.setData(activity, editViewModel, this)
            }
            .setNegativeButton("no", null)
            .setCancelable(false)
            .show()*/
    }
}