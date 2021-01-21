package com.wsr.katanarecorder.ui.detail.edit

import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.wsr.katanarecorder.BuildConfig
import com.wsr.katanarecorder.view_model.EditViewModel
import java.io.File
import java.io.InputStream
import java.util.*

//画像を取り出すときに使うクラス
class DetailEditImageSetter(
    private val activity: FragmentActivity,
    private val editViewModel: EditViewModel,
    private var resetController: (Uri) -> Unit
) : DefaultLifecycleObserver {
    private lateinit var getContent: ActivityResultLauncher<String>
    private lateinit var dispatchTakePicture: ActivityResultLauncher<Uri>

    private val registry = activity.activityResultRegistry

    private lateinit var uri: Uri

    override fun onCreate(owner: LifecycleOwner) {
        //SAFの呼び出しや、選択後の挙動を設定
        getContent =
            registry.register(
                "key",
                owner,
                ActivityResultContracts.GetContent()){
                it?.let{
                    resetController(it)
                }
            }

        dispatchTakePicture =
            registry.register(
                "keys",
                owner,
                ActivityResultContracts.TakePicture()){
                    Log.d("TEST", it.toString())
                    resetController(uri)
                }
    }

    //画像選択をするための関数
    fun selectImage() {
        //getContent.launch("image/*")

        val filename = UUID.randomUUID().toString() + ".jpg"
        val path = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File(path, filename)

        uri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", file)

        takePicture()
    }

    private fun takePicture() {
        dispatchTakePicture.launch(uri)
        Log.d("Uri", uri.toString())

    }

    //editViewModelに保管されている画像のinputStream型を取るための関数
    fun getInputStream(): InputStream?{
        editViewModel.getInputStream()?.let{
            return it
        }
        return null
    }
}