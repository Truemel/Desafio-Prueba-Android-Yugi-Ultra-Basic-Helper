package com.example.yu_gi_ohultrabasichelper.modeloh.room

import androidx.lifecycle.LiveData
import com.example.yu_gi_ohultrabasichelper.modeloh.retrofit.CardDataRetro

class YugiDBManager(private val yugiDAO: YugiDAO) {

    suspend fun insertAllSets(list:MutableList<YugiSetTablePojo>){
        yugiDAO.insertAllSets(list)
    }

    suspend fun insertAllCardsFromSet(list:MutableList<CardDataRetro>, set:String){
        val cards:MutableList<YugiCardTablePojo> = mutableListOf()
        for(card in list)
            cards.add(YugiCardTablePojo(card.name, set, card.text, card.card_type, card.type, card.family, card.atk, card.def, card.level, card.property))
        yugiDAO.insertAllCardsFromSet(cards)
    }

    suspend fun insertFavCard(card: CardDataRetro){
        yugiDAO.insertFavCard(YugiFavouriteTablePojo(card.name, card.text, card.card_type, card.type, card.family, card.atk, card.def, card.level, card.property))
    }

    suspend fun updateFavCard(card:YugiFavouriteTablePojo){
        
    }

    suspend fun deleteFavCard(card:YugiFavouriteTablePojo)

    suspend fun deleteAllFavCard()

    fun getSetsList(): LiveData<MutableList<YugiSetTablePojo>>

    fun getCardsListFromSets(set:String): LiveData<MutableList<YugiCardTablePojo>>

    fun getFavCardsList(): LiveData<MutableList<YugiFavouriteTablePojo>>

}