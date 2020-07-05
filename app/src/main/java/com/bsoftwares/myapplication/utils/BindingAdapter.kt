package com.bsoftwares.myapplication.utils

import android.graphics.Paint
import android.media.Image
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.andremion.counterfab.CounterFab
import com.bsoftwares.myapplication.R
import com.bsoftwares.myapplication.model.Spotlight
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import ru.nikartm.support.ImageBadgeView

@BindingAdapter("addSalesLine")
fun TextView.addSalesLine(boolean: Boolean) {
    if (boolean)
        paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}

@BindingAdapter("loadImage")
fun ImageView.loadImage(url : String?) {
    clipToOutline = true
    Picasso.get().load(url).fit().centerCrop().into(this)
}

@BindingAdapter("changeBackground")
fun FloatingActionButton.changeBackground(gameIsIn : Boolean){
    if (gameIsIn)
        setImageResource(R.drawable.ic_baseline_remove_shopping_cart)
    else
        setImageResource(R.drawable.ic_baseline_shopping_cart)

}