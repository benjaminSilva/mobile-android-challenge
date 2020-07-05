package com.bsoftwares.myapplication.model

import androidx.room.PrimaryKey
import com.bsoftwares.myapplication.database.CartDB
import com.bsoftwares.myapplication.network.SpotlightNW

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
    val newPrice:Int,
    val quantities: Int
)

data class GameSearchResult(
    val id: Int,
    val title: String,
    val discount: Int,
    val price: Int,
    val newPrice: Int
)

data class Cart(
    val id: Int,
    val title:String,
    val image:String,
    val discount:Int,
    var isAdded:Boolean,
    val price:Int,
    val newPrice:Int,
    var totalPrice:Int,
    var quantities:Int,
    var totalNewPrice:Int
)

fun Spotlight.asCartDatabase(): CartDB {
    return CartDB(
        title = title,
        id = id,
        image = image,
        quantities = quantities,
        discount = discount,
        price = price,
        isAdded = false
    )
}

fun Cart.asDataBase() : CartDB{
    return CartDB(
        title = title,
        id = id,
        image = image,
        quantities = quantities,
        discount = discount,
        price = price,
        isAdded = false
    )
}