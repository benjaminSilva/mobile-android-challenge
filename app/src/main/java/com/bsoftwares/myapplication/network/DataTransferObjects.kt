package com.bsoftwares.myapplication.network

import com.bsoftwares.myapplication.database.BannerDB
import com.bsoftwares.myapplication.database.GamesDataBase
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkBanner(val banners : List<BannerNW>)

@JsonClass(generateAdapter = true)
data class BannerNW(
    val id : Int,
    val url : String,
    val img : String
)

fun NetworkBanner.asDatabaseModel() : Array<BannerDB>{
    return banners.map {
        BannerDB(
            url = it.url,
            id = it.id,
            img = it.img
        )
    }.toTypedArray()
}