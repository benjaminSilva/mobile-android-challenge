package com.bsoftwares.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bsoftwares.myapplication.database.*
import com.bsoftwares.myapplication.model.*
import com.bsoftwares.myapplication.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//Decidi manter apenas um respositório para esse app, mas o ideal seriam vários dependendo da complexidade do APP
class GamesRepository(private val database: GamesDataBase) {

    val banners: LiveData<List<Banner>> = Transformations.map(database.gamesDAO.getBannersDB()) {
        it.asBannerDomain()
    }

    val spotlights: LiveData<List<Spotlight>> =
        Transformations.map(database.gamesDAO.getSpotlightDB()) {
            it.asSpotLightDomain()
        }

    val cartList : LiveData<List<Cart>> = Transformations.map(database.gamesDAO.getCartsDB()){
        it.asCartDomain()
    }



    //Carregar is Banners e lista de jogos
    suspend fun refreshGames() {
        withContext(Dispatchers.IO) {
            val banner = Network.gameshop.getBanners().await()
            val spotlights = Network.gameshop.getSpotlight().await()
            database.gamesDAO.insertBanners(*banner.asBannedToDatabase())
            database.gamesDAO.insertSpotlights(*spotlights.asSpotlightToDatabase())
        }
    }

    //Detalhe de um jogo específico
    val gameDetails: LiveData<Spotlight>
        get() = _gameDetails
    private val _gameDetails = MutableLiveData<Spotlight>()

    suspend fun loadGameDetais(gameID: Int) {
        withContext(Dispatchers.IO) {
            val gameDetail = Network.gameshop.getGamesDetail(gameID).await()
            _gameDetails.postValue(gameDetail.asDomainSpotlight())
        }
    }

    //Busca
    val searchGames: LiveData<List<GameSearchResult>>
        get() = _searchGames
    private val _searchGames = MutableLiveData<List<GameSearchResult>>()

    suspend fun searchGames(busca: String) {
        withContext(Dispatchers.IO) {
            val result = Network.gameshop.getSearchResult(busca).await()
            _searchGames.postValue(result.asSearchResultDomain())
        }
    }

    //Lista de compras
    /*val currentGame : LiveData<Cart> = Transformations.map(database.gamesDAO.getCartDB(gameDetails.value!!.id)){
        it.asCartDomain()
    }*/

    val currentGame: LiveData<CartDB>
        get() = _currentGame
    private val _currentGame = MutableLiveData<CartDB>()

    suspend fun addGame() {
        withContext(Dispatchers.IO) {
            database.gamesDAO.insertCart(gameDetails.value!!.asCartDatabase())
        }
    }

    suspend fun deleteGame(cart : CartDB) {
        withContext(Dispatchers.IO) {
            database.gamesDAO.deleteCart(cart)
        }
    }

    suspend fun updateGame(cart : CartDB) {
        withContext(Dispatchers.IO){
            database.gamesDAO.updateCart(cart)
        }
    }

    suspend fun checkGame(){
        withContext(Dispatchers.IO){
            val currentGameID = gameDetails.value!!.id
            _currentGame.postValue(database.gamesDAO.getCartDB(currentGameID))
        }

    }


}