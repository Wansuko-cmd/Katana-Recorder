package com.wsr.katanarecorder.ui.detail.edit

import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.wsr.katanarecorder.view_model.EditViewModel
import java.io.InputStream

class DetailEditImageSetter(
    private val activity: FragmentActivity,
    private val editViewModel: EditViewModel,
    private val controller: DetailEditEpoxyController
) : DefaultLifecycleObserver {
    lateinit var getContent: ActivityResultLauncher<String>
    private val registry = activity.activityResultRegistry

    private var url: Uri? = null

    override fun onCreate(owner: LifecycleOwner) {
        getContent =
            registry.register(
                "key",
                owner,
                ActivityResultContracts.GetContent(),
                ActivityResultCallback {
                    it?.let{
                        Log.d("result", "fragment: $it")
                        url = it
                        editViewModel.setUrl(it)
                    }
                    controller.setData(activity, editViewModel)
                    println("Here")
                })
    }

    fun selectImage() {
        getContent.launch("image/*")
    }

    fun getInputStream(): InputStream?{
        editViewModel.getInputStream(activity)?.let{
            return it
        }
        return null
    }
}