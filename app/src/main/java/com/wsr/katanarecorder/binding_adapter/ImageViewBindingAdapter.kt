package com.wsr.katanarecorder.binding_adapter

import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.wsr.katanarecorder.R
import java.io.InputStream

object ImageViewBindingAdapter {

    //InputStreamを投げたらBitmapに変換して表示してくれるやつ
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.imageUrl(inputStream: InputStream?){
        inputStream?.let{
            val image = BitmapFactory.decodeStream(it)
            this.setImageBitmap(image)
            Log.i("imageURL", "Reflected to $inputStream")
            return
        }
        this.setImageResource(R.drawable.ic_add_a_photo)
    }
}
