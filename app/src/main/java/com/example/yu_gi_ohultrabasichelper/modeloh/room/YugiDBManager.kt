package com.example.yu_gi_ohultrabasichelper.modeloh.room

import androidx.lifecycle.LiveData
import com.example.yu_gi_ohultrabasichelper.modeloh.retrofit.CardDataRetro
import com.example.yu_gi_ohultrabasichelper.modeloh.retrofit.CardsRetro
import com.example.yu_gi_ohultrabasichelper.modeloh.retrofit.YugiSetsRetroPojo

class YugiDBManager(private val yugiDAO: YugiDAO) {

    suspend fun insertAllSets(setList:YugiSetsRetroPojo){
        val sets:MutableList<YugiSetTablePojo> = mutableListOf()
        for(set in setList.list)
            sets.add(YugiSetTablePojo(set))
        yugiDAO.insertAllSets(sets)
    }

    suspend fun insertAllCardsFromSet(list:MutableList<CardsRetro>, set:String){
        val cards:MutableList<YugiCardTablePojo> = mutableListOf()
        for(card in list)
            cards.add(YugiCardTablePojo(card.name, set))
        yugiDAO.insertAllCardsFromSet(cards)
    }

    suspend fun insertFavCard(card: CardDataRetro){
        yugiDAO.insertFavCard(YugiFavouriteTablePojo(card.name, card.text, card.card_type, card.type, card.family, card.atk, card.def, card.level, card.property))
    }

    suspend fun updateFavCard(card:YugiFavouriteTablePojo){
        yugiDAO.updateFavCard(card)
    }

    suspend fun deleteFavCard(card:YugiFavouriteTablePojo){
        yugiDAO.deleteFavCard(card)
    }

    suspend fun deleteAllFavCard(){
        yugiDAO.deleteAllFavCard()
    }

    fun getSetsList(): LiveData<MutableList<YugiSetTablePojo>>{
        return yugiDAO.getSetsList()
    }

    fun getCardsListFromSets(set:String): LiveData<MutableList<YugiCardTablePojo>>{
        return yugiDAO.getCardsListFromSets(set)
    }

    fun getFavCardsList(): LiveData<MutableList<YugiFavouriteTablePojo>>{
        return yugiDAO.getFavCardsList()
    }

}