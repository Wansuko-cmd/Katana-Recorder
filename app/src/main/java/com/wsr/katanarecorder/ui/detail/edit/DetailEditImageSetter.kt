package com.wsr.katanarecorder.ui.detail.edit

import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.wsr.katanarecorder.view_model.EditViewModel
import java.io.InputStream

//画像を取り出すときに使うクラス
class DetailEditImageSetter(
    activity: FragmentActivity,
    private val editViewModel: EditViewModel,
    private var resetController: (Uri) -> Unit
) : DefaultLifecycleObserver {
    lateinit var getContent: ActivityResultLauncher<String>
    private val registry = activity.activityResultRegistry

    override fun onCreate(owner: LifecycleOwner) {
        getContent =
            registry.register(
                "key",
                owner,
                ActivityResultContracts.GetContent(),
                {
                    it?.let{
                        Log.d("result", "fragment: $it")
                        resetController(it)
                    }
                })
    }

    //画像選択をするための関数
    fun selectImage() {
        getContent.launch("image/*")
    }

    //editViewModelに保管されている画像のinputStream型を取るための関数
    fun getInputStream(): InputStream?{
        editViewModel.getInputStream()?.let{
            return it
        }
        return null
    }
}