package com.bsoftwares.myapplication.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GamesDAO{
    @Query("select * from BannerDB")
    fun getBannersDB(): LiveData<List<BannerDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg banner: BannerDB)
}

@Database(entities = [BannerDB::class],version = 1)
abstract class GamesDataBase : RoomDatabase(){
    abstract val gamesDAO : GamesDAO
}

private lateinit var INSTANCE : GamesDataBase

fun getDataBase(context: Context) : GamesDataBase{
    if(!::INSTANCE.isInitialized)
        INSTANCE = Room.databaseBuilder(context.applicationContext,
        GamesDataBase::class.java,
        "games").build()
    return INSTANCE
}