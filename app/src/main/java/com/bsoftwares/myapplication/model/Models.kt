package com.bsoftwares.myapplication.model

data class Banner(
    val id: Int,
    val url: String,
    val img: String
)

data class Spotlight(
    val id: Int,
    val title:String,
    val publisher :String,
    val image:String,
    val discount:Int,
    val price:Int,
    val description:String,
    val rating:Float,
    val stars:Int,
    val reviews:Int,
    val newPrice:Int
)

data class GameSearchResult(
    val id: Int,
    val title: String,
    val discount: Int,
    val price: Int,
    val newPrice: Int
)