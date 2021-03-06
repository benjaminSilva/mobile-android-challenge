package com.bsoftwares.myapplication.network

import com.bsoftwares.myapplication.model.Spotlight
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameShopService {
    @GET("banners")
    fun getBanners(): Deferred<List<BannerNW>>

    @GET("spotlight")
    fun getSpotlight(): Deferred<List<SpotlightNW>>

    @GET("games/{gameID}")
    fun getGamesDetail(@Path("gameID") gameID : Int) : Deferred<SpotlightNW>

    @GET("games/search")
    fun getSearchResult(@Query("term") busca : String) : Deferred<List<GameSearchResultNW>>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api-mobile-test.herokuapp.com/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val gameshop = retrofit.create(GameShopService::class.java)
}
