package com.bsoftwares.myapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bsoftwares.myapplication.model.Banner
import com.bsoftwares.myapplication.model.Spotlight

@Entity
data class BannerDB constructor(
    @PrimaryKey
    val id: Int,
    val url: String,
    val img : String
)

@Entity
data class SpotlightDB constructor(
    @PrimaryKey
    val id: Int,
    val title:String,
    val publisher :String,
    val image:String,
    val discount:Int,
    val price:Int,
    val description:String,
    val rating: Float,
    val stars:Int,
    val reviews:Int
)

fun List<BannerDB>.asBannerDomain(): List<Banner>{
    return map {
        Banner(
            url = it.url,
            id = it.id,
            img = it.img
        )
    }
}

fun List<SpotlightDB>.asSpotLightDomain(): List<Spotlight>{
    return map {
        Spotlight(
            id = it.id,
            title = it.title,
            publisher = it.publisher,
            image = it.image,
            discount = it.discount,
            price = it.price,
            description = it.description,
            rating = it.rating,
            stars = it.stars,
            reviews = it.reviews,
            newPrice = it.price - it.discount
        )
    }
}