package com.xridwan.moviecatalogue3.utils

import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.xridwan.moviecatalogue3.BuildConfig
import com.xridwan.moviecatalogue3.R

object Ext {
    fun ImageView.loadPoster(url: String?) {
        Glide.with(this.context)
            .load(BuildConfig.IMAGE_URL + url)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(this)
    }

    fun ImageView.loadBackdrop(url: String?) {
        Glide.with(this.context)
            .load(BuildConfig.IMAGE_URL + url)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(this)
    }

    fun Activity.onToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Fragment.onToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}