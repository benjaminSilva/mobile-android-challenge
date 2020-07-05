package com.bsoftwares.myapplication.database

import android.content.Context
import android.content.LocusId
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GamesDAO{
    @Query("select * from BannerDB")
    fun getBannersDB(): LiveData<List<BannerDB>>

    @Query("select * from SpotlightDB")
    fun getSpotlightDB(): LiveData<List<SpotlightDB>>

    @Query("select * from CartDB")
    fun getCartsDB() : LiveData<List<CartDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBanners(vararg banner: BannerDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpotlights(vararg spotlight : SpotlightDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCart(vararg cart : CartDB)

    @Query("SELECT * FROM cartdb WHERE id = :cartID")
    fun getCartDB(cartID : Int) : CartDB



    /*@Query("DELETE from cartdb WHERE id =:gameID")
    fun deleteCart(gameID : Int)*/

    /*@Query("Update cartdb SET quantities =:quantitiy where id =:gameID")
    fun updateItem(quantitiy : Int,gameID: Int)*/

    @Update
    fun updateCart(vararg cart : CartDB)

    @Delete
    fun deleteCart(cart : CartDB)

}

@Database(entities = [BannerDB::class,SpotlightDB::class,CartDB::class],version = 1)
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