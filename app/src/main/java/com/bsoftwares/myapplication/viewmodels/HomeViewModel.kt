package com.bsoftwares.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.bsoftwares.myapplication.database.getDataBase
import com.bsoftwares.myapplication.repository.GamesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val dataBase = getDataBase(application)
    private val gameRepository = GamesRepository(dataBase)

    init {
        viewModelScope.launch {
            gameRepository.refreshGames()
        }
    }

    val banners = gameRepository.banners


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }
}