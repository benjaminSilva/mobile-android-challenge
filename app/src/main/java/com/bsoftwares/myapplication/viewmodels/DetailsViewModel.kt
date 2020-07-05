package com.bsoftwares.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.bsoftwares.myapplication.database.getDataBase
import com.bsoftwares.myapplication.model.Cart
import com.bsoftwares.myapplication.repository.GamesRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val dataBase = getDataBase(application)
    private val gameRepository = GamesRepository(dataBase)

    var game : Int = 1

    init {
        viewModelScope.launch {
            gameRepository.loadGameDetais(game)
            gameRepository.checkGame()
        }
    }


    var gameDetail = gameRepository.gameDetails
    var currentGame = gameRepository.currentGame

    fun addItem(){
        viewModelScope.launch {
            gameRepository.addGame()
        }
    }

    val isGameThere : LiveData<Boolean>
        get() = _isGameThere
    val _isGameThere = MutableLiveData<Boolean>()

    fun addOrRemove(){
        viewModelScope.launch {
            gameRepository.checkGame()
        }
    }

    fun deleteItem(){
        viewModelScope.launch {
            gameRepository.deleteGame(currentGame.value!!)
        }
    }

    val snackbar : LiveData<String>
        get() = _snackBar
    val _snackBar = MutableLiveData<String>()

    fun gameIsIn() {
        deleteItem()
        _snackBar.value = "Removido"
        updateGameStatus(false)
    }

    fun gameIsOut() {
        addItem()
        _snackBar.value = "Adicionado"
        updateGameStatus(true)
    }

    fun updateGameStatus(status : Boolean){
        _isGameThere.value = status
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailsViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }
}