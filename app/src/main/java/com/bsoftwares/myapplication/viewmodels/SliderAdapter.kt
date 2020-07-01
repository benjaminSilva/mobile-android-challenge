package com.bsoftwares.myapplication.viewmodels

import android.content.Context
import android.widget.ImageView
import com.bsoftwares.myapplication.model.Banner
import com.squareup.picasso.Picasso
import ss.com.bannerslider.ImageLoadingService
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder


class BannerAdapter(val banners : List<Banner>) : SliderAdapter() {
    override fun getItemCount(): Int {
        return banners.size
    }

    override fun onBindImageSlide(position: Int, imageSlideViewHolder: ImageSlideViewHolder?) {
        imageSlideViewHolder!!.bindImageSlide(banners[position].img)
    }

}

class PicassoImageLoadingService(context: Context) : ImageLoadingService {
    var context: Context
    override fun loadImage(url: String?, imageView: ImageView?) {
        Picasso.with(context).load(url).into(imageView)
    }

    override fun loadImage(resource: Int, imageView: ImageView?) {
        Picasso.with(context).load(resource).into(imageView)
    }

    override fun loadImage(
        url: String?,
        placeHolder: Int,
        errorDrawable: Int,
        imageView: ImageView?
    ) {
        Picasso.with(context).load(url).placeholder(placeHolder).error(errorDrawable)
            .into(imageView)
    }

    init {
        this.context = context
    }
}