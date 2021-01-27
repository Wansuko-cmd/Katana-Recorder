package com.wsr.katanarecorder.ui.detail.edit

import android.text.Editable
import android.text.TextWatcher
import com.wsr.katanarecorder.view_model.EditViewModel

//文字の変更を認識するためのクラス
class DetailEditTextWatcher(
    val key: String,
    private val editViewModel: EditViewModel,
    private val kind: Int
    ) : TextWatcher{
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(p0: Editable?) {
        when(kind){
            2 -> editViewModel.setTitle(p0.toString())
            3 -> editViewModel.setValue(key, p0.toString())
        }
    }
}