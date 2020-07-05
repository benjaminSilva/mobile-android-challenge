package com.bsoftwares.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.bsoftwares.myapplication.database.getDataBase
import com.bsoftwares.myapplication.model.Cart
import com.bsoftwares.myapplication.model.asDataBase
import com.bsoftwares.myapplication.repository.GamesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {


    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val dataBase = getDataBase(application)
    private val gameRepository = GamesRepository(dataBase)


    val listaGames = gameRepository.cartList

    val oldTotal: LiveData<Int>
        get() = _oldTotal
    val _oldTotal = MutableLiveData<Int>()

    val newTotal: LiveData<Int>
        get() = _newTotal
    val _newTotal = MutableLiveData<Int>()

    fun updateCart(game: Cart) {
        viewModelScope.launch {
            gameRepository.updateGame(game.asDataBase())
        }
    }

    fun deleteGame(game: Cart) {
        viewModelScope.launch {
            gameRepository.deleteGame(game.asDataBase())
        }
    }

    fun sumTotal(){
        var totalPrice = 0
        var totalNewPrice = 0
        for(game : Cart in listaGames.value!!){
            totalPrice += game.price * game.quantities
            totalNewPrice += game.newPrice * game.quantities
        }
        _oldTotal.value = totalPrice
        _newTotal.value = totalNewPrice
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CartViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }
}