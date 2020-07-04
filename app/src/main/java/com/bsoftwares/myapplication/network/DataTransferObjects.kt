package com.bsoftwares.myapplication.network

import androidx.lifecycle.Transformations.map
import com.bsoftwares.myapplication.database.BannerDB
import com.bsoftwares.myapplication.database.GamesDataBase
import com.bsoftwares.myapplication.database.SpotlightDB
import com.bsoftwares.myapplication.model.GameSearchResult
import com.bsoftwares.myapplication.model.Spotlight
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BannerNW(
    val id: Int,
    val url: String,
    val image: String
)

@JsonClass(generateAdapter = true)
data class SpotlightNW(
    val id: Int,
    val title: String,
    val publisher: String,
    val image: String,
    val discount: Int,
    val price: Int,
    val description: String,
    val rating: Float,
    val stars: Int,
    val reviews: Int
)

@JsonClass(generateAdapter = true)
data class GameSearchResultNW(
    val id: Int,
    val title: String,
    val discount: Int,
    val price: Int
)

fun List<BannerNW>.asBannedToDatabase(): Array<BannerDB> {
    return map {
        BannerDB(
            url = it.url,
            id = it.id,
            img = it.image
        )
    }.toTypedArray()
}

fun List<SpotlightNW>.asSpotlightToDatabase(): Array<SpotlightDB> {
    return map {
        SpotlightDB(
            id = it.id,
            title = it.title,
            publisher = it.publisher,
            image = it.image,
            discount = it.discount,
            price = it.price,
            description = it.description,
            rating = it.rating,
            stars = it.stars,
            reviews = it.reviews
        )
    }.toTypedArray()
}

fun List<GameSearchResultNW>.asSearchResultDomain(): List<GameSearchResult> {
    return map {
        GameSearchResult(
            id = it.id,
            title = it.title,
            discount = it.discount,
            price = it.price,
            newPrice = it.price - it.discount
        )
    }
}

fun SpotlightNW.asDomainSpotlight(): Spotlight {
    return Spotlight(
        id = this.id,
        title = this.title,
        publisher = this.publisher,
        image = this.image,
        discount = this.discount,
        price = this.price,
        description = this.description,
        rating = this.rating,
        stars = this.stars,
        reviews = this.reviews,
        newPrice = this.price - this.discount
    )
}