package com.wsr.katanarecorder.binding_adapter

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import java.io.InputStream

object ImageViewBindingAdapter {

    //InputStreamを投げたらBitmapに変換して表示してくれるやつ
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.imageUrl(inputStream: InputStream?){
        inputStream?.let{
            val image = BitmapFactory.decodeStream(it)
            this.setImageBitmap(image)
        }
    }

}
