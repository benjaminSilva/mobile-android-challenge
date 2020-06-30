package com.bsoftwares.myapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bsoftwares.myapplication.model.Banner

@Entity
data class BannerDB constructor(
    @PrimaryKey
    val id: Int,
    val url: String,
    val img : String
)

fun List<BannerDB>.asDomainModel(): List<Banner>{
    return map {
        Banner(
            url = it.url,
            id = it.id,
            img = it.img
        )
    }
}