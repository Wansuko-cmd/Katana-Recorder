package com.wsr.katanarecorder.ui.detail.edit

import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.wsr.katanarecorder.view_model.EditViewModel

class DetailEditImageSetter(activity: FragmentActivity, editViewModel: EditViewModel) : DefaultLifecycleObserver {
    lateinit var getContent: ActivityResultLauncher<String>
    private val registry = activity.activityResultRegistry

    override fun onCreate(owner: LifecycleOwner) {
        getContent =
            registry.register(
                "key",
                owner,
                ActivityResultContracts.GetContent(),
                ActivityResultCallback {
                    Log.d("result", "fragment: $it")
                })
    }

    fun selectImage() {
        getContent.launch("image/*")
    }
}