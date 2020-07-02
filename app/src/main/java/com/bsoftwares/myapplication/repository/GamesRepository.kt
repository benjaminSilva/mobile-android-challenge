package com.bsoftwares.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.bsoftwares.myapplication.database.GamesDataBase
import com.bsoftwares.myapplication.database.asBannerDomain
import com.bsoftwares.myapplication.database.asSpotLightDomain
import com.bsoftwares.myapplication.model.Banner
import com.bsoftwares.myapplication.model.Spotlight
import com.bsoftwares.myapplication.network.Network
import com.bsoftwares.myapplication.network.asBannedToDatabase
import com.bsoftwares.myapplication.network.asSpotlightToDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GamesRepository(private val database : GamesDataBase){

    val banners : LiveData<List<Banner>> = Transformations.map(database.gamesDAO.getBannersDB()){
        it.asBannerDomain()
    }

    val spotlights : LiveData<List<Spotlight>> = Transformations.map(database.gamesDAO.getSpotlightDB()){
        it.asSpotLightDomain()
    }

    /*fun getDataRoom(){
        banners = Transformations.map(database.gamesDAO.getBanners()){
            it.asDomainModel()
        }
    }*/

    suspend fun refreshGames(){
        withContext(Dispatchers.IO){
            val banner = Network.gameshop.getBanners().await()
            val spotlights = Network.gameshop.getSpotlight().await()
            database.gamesDAO.insertBanners(*banner.asBannedToDatabase())
            database.gamesDAO.insertSpotlights(*spotlights.asSpotlightToDatabase())
        }
    }
}