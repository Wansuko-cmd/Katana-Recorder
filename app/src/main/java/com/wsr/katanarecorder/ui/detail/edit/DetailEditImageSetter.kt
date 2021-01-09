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

class DetailEditImageSetter(
    private val activity: FragmentActivity,
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

    fun selectImage() {
        getContent.launch("image/*")
    }

    fun getInputStream(): InputStream?{
        editViewModel.getInputStream()?.let{
            return it
        }
        return null
    }
}