package com.bsoftwares.myapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bsoftwares.myapplication.model.Banner
import com.bsoftwares.myapplication.model.Cart
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

@Entity
data class CartDB constructor(
    @PrimaryKey
    val id: Int,
    val title:String,
    val image:String,
    val discount:Int,
    val price:Int,
    var quantities :Int,
    var isAdded : Boolean
)

fun List<CartDB>.asCartDomain() : List<Cart>{
    return map {
        Cart(
            title = it.title,
            id = it.id,
            image = it.image,
            quantities = it.quantities,
            discount = it.discount,
            newPrice = it.price - it.discount,
            totalNewPrice = (it.price - it.discount) * it.quantities,
            totalPrice = it.price * it.quantities,
            price = it.price,
            isAdded = it.isAdded
        )
    }
}

fun List<BannerDB>.asBannerDomain(): List<Banner>{
    return map {
        Banner(
            url = it.url,
            id = it.id,
            img = it.img
        )
    }
}

fun CartDB.asCartDomain(): Cart{
    return Cart(
        title = title,
        id = id,
        image = image,
        quantities = quantities,
        discount = discount,
        newPrice = price - discount,
        totalNewPrice = (price - discount) * quantities,
        totalPrice = price * quantities,
        price = price,
        isAdded = isAdded
    )
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
            newPrice = it.price - it.discount,
            quantities = 1
        )
    }
}