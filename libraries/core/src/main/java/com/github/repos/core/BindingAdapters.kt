package com.github.repos.core

import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.TouchDelegate
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.repos.core.lists.DataBoundModel
import com.github.repos.core.lists.DataBoundRecyclerAdapter
import com.google.android.material.imageview.ShapeableImageView


@BindingAdapter("android:src")
fun ImageView.setImageViewResource(@DrawableRes resource: Int?) {
    if (resource != null) {
        setImageResource(resource)
    }
}

@BindingAdapter(value = ["items", "lifecycleOwner"], requireAll = false)
fun RecyclerView.setItems(items: List<DataBoundModel>?, lifecycleOwner: LifecycleOwner? = null) {
    items?.let {
        adapter = DataBoundRecyclerAdapter(it, lifecycleOwner)
    }
}

@BindingAdapter(value = ["imageUrl"], requireAll = false)
fun ShapeableImageView.setImageUrl(url: String?) {
    if (url != null) {
        this.post {
            val requestBuilder = Glide.with(this)
                .load(url)
                .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.black)))
                .error(ColorDrawable(ContextCompat.getColor(context, R.color.black)))
                .transition(DrawableTransitionOptions.withCrossFade(300))

            requestBuilder.into(this)
        }
    }
}

@BindingAdapter(value = ["imageUrl", "centerCrop", "fitCenter"], requireAll = false)
fun ImageView.setImageUrl(url: String?, centerCrop: Boolean = true, fitCenter: Boolean = false) {
    if (url != null) {
        this.post {
            val requestBuilder = Glide.with(this)
                .load(url)
                .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.black)))
                .error(ColorDrawable(ContextCompat.getColor(context, R.color.black)))
                .transition(DrawableTransitionOptions.withCrossFade(300))
            if (centerCrop) {
                requestBuilder.centerCrop()
            }
            if (fitCenter) {
                requestBuilder.fitCenter()
            }
            requestBuilder.into(this)
        }
    }
}

@BindingAdapter("onTouchListener")
fun View.setOnTouchListener(touchListener: View.OnTouchListener?) {
    this.setOnTouchListener(touchListener)
}

@BindingAdapter("selected")
fun View.setIsSelected(isSelected: Boolean) {
    this.isSelected = isSelected
}

@BindingAdapter("app:lottie_rawRes")
fun LottieAnimationView.setAnimation(@RawRes resource: Int?) {
    if (resource != null)
        this.setAnimation(resource)
}
