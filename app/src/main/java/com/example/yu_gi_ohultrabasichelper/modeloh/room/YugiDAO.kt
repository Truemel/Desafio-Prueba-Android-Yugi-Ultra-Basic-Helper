package com.example.yu_gi_ohultrabasichelper.modeloh.room

import androidx.lifecycle.LiveData
import androidx.room.*
import retrofit2.http.DELETE

@Dao
interface YugiDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSets(list:MutableList<YugiSetTablePojo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCardsFromSet(list:MutableList<YugiCardTablePojo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavCard(card:YugiFavouriteTablePojo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCardData(card:YugiCardDataTablePojo)

    @Update
    suspend fun updateFavCard(card:YugiFavouriteTablePojo)

    @DELETE
    suspend fun deleteFavCard(card:YugiFavouriteTablePojo)

    @Query("DELETE FROM fav_table")
    suspend fun deleteAllFavCard()

    @Query("SELECT * FROM sets_table ORDER BY name")
    fun getSetsList():LiveData<MutableList<YugiSetTablePojo>>

    @Query("SELECT * FROM card_table WHERE 'set' = :set ORDER BY name")
    fun getCardsListFromSets(set:String):LiveData<MutableList<YugiCardTablePojo>>

    @Query("SELECT * FROM fav_table ORDER BY name")
    fun getFavCardsList():LiveData<MutableList<YugiFavouriteTablePojo>>

    @Query("SELECT * FROM card_data_table WHERE 'card' = :card")
    fun getCardData(card:String):LiveData<YugiCardDataTablePojo>

}