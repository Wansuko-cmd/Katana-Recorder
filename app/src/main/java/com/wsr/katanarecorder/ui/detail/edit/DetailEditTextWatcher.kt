package com.wsr.katanarecorder.ui.detail.edit

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.wsr.katanarecorder.view_model.EditViewModel

class DetailEditTextWatcher(val key: String, private val editViewModel: EditViewModel) : TextWatcher{
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(p0: Editable?) {
        editViewModel.setValue(key, p0.toString())
        Log.i("key", key)
        Log.i("List", p0.toString())
    }
}