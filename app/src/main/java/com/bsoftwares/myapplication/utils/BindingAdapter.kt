package com.bsoftwares.myapplication.utils

import android.graphics.Paint
import android.media.Image
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bsoftwares.myapplication.model.Spotlight
import com.squareup.picasso.Picasso

@BindingAdapter("addSalesLine")
fun TextView.addSalesLine(boolean: Boolean) {
    if (boolean)
        paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String) {
    clipToOutline = true
    Picasso.get().load(url).fit().centerCrop().into(this)
}