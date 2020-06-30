package com.bsoftwares.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.bsoftwares.myapplication.database.GamesDataBase
import com.bsoftwares.myapplication.database.asDomainModel
import com.bsoftwares.myapplication.model.Banner
import com.bsoftwares.myapplication.network.Network
import com.bsoftwares.myapplication.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GamesRepository(private val database : GamesDataBase){

    val banners : LiveData<List<Banner>> = Transformations.map(database.gamesDAO.getBanners()){
        it.asDomainModel()
    }

    suspend fun refreshGames(){
        withContext(Dispatchers.IO){
            val banner = Network.gameshop.getBanners().await()
            database.gamesDAO.insertAll(*banner.asDatabaseModel())
        }
    }
}