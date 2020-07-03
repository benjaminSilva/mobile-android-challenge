package com.bsoftwares.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bsoftwares.myapplication.database.GamesDataBase
import com.bsoftwares.myapplication.database.asBannerDomain
import com.bsoftwares.myapplication.database.asSpotLightDomain
import com.bsoftwares.myapplication.model.Banner
import com.bsoftwares.myapplication.model.Spotlight
import com.bsoftwares.myapplication.network.Network
import com.bsoftwares.myapplication.network.asBannedToDatabase
import com.bsoftwares.myapplication.network.asDomainSpotlight
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

    //Decidi manter apenas um respositório para esse app, mas o idela seriam vários dependendo da complexidade do APP

    val gameDetails : LiveData<Spotlight>
        get() = _gameDetails
    private val _gameDetails = MutableLiveData<Spotlight>()

    suspend fun loadGameDetais(gameID : Int){
        withContext(Dispatchers.IO){
            val gameDetail = Network.gameshop.getGamesDetail(gameID).await()
            _gameDetails.postValue(gameDetail.asDomainSpotlight())
        }
    }
}