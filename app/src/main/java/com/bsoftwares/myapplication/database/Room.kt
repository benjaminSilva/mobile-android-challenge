package com.bsoftwares.myapplication.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GamesDAO{
    @Query("select * from bannerdb")
    fun getBannersDB(): LiveData<List<BannerDB>>

    @Query("select * from spotlightdb")
    fun getSpotlightDB(): LiveData<List<SpotlightDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBanners(vararg banner: BannerDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpotlights(vararg spotlight : SpotlightDB)
}

@Database(entities = [BannerDB::class,SpotlightDB::class],version = 1)
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