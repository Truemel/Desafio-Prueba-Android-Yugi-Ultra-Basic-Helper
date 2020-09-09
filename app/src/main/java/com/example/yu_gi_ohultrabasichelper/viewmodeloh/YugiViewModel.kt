package com.example.yu_gi_ohultrabasichelper.viewmodeloh

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.yu_gi_ohultrabasichelper.modeloh.retrofit.*
import com.example.yu_gi_ohultrabasichelper.modeloh.room.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YugiViewModel(application: Application) : AndroidViewModel(application) {

    private val dbManager:YugiDBManager
    val yugiSetsList:LiveData<MutableList<YugiSetTablePojo>>
    lateinit var yugiCardsList:LiveData<MutableList<YugiCardTablePojo>>
    val yugiFavList:LiveData<MutableList<YugiFavouriteTablePojo>>

    init {
        dbManager = YugiDBManager(YugiDB.getDB(application).getYugiDAO())
        yugiSetsList = dbManager.getSetsList()
        yugiFavList = dbManager.getFavCardsList()

        if(yugiSetsList.value == null || yugiSetsList.value!!.size == 0)
            getSetListFromRetro()
    }

    fun setCardsListToViewModel(set:String):Boolean{
        var haveCards:Boolean = false

        yugiCardsList = dbManager.getCardsListFromSets(set)

        if(yugiCardsList.value != null && yugiCardsList.value!!.size > 0)
            haveCards = true

        return haveCards
    }

    fun insertAllSets(setList:YugiSetsRetroPojo) = viewModelScope.launch { dbManager.insertAllSets(setList) }

    fun insertAllCardsFromSet(list:MutableList<CardsRetro>, set: String) = viewModelScope.launch { dbManager.insertAllCardsFromSet(list, set) }

    fun insertFavCard(card:CardDataRetro) = viewModelScope.launch { dbManager.insertFavCard(card) }

    fun updateFavCard(card:YugiFavouriteTablePojo) = viewModelScope.launch { dbManager.updateFavCard(card) }

    fun deleteFavCard(card:YugiFavouriteTablePojo) = viewModelScope.launch { dbManager.deleteFavCard(card) }

    fun deleteAllFavCard() = viewModelScope.launch { dbManager.deleteAllFavCard() }

    fun getSetListFromRetro(){
        YugiRetrofitRequests().getYugiSetsList(object : Callback<YugiSetsRetroPojo>{
            override fun onResponse(
                call: Call<YugiSetsRetroPojo>,
                response: Response<YugiSetsRetroPojo>
            ) {
                if(response.isSuccessful && response.body()!!.list.size > 0)
                    insertAllSets(response.body()!!)
            }

            override fun onFailure(call: Call<YugiSetsRetroPojo>, t: Throwable) {
                Toast.makeText(getApplication(), "Error, couldn't sets list", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getCardListFromSetFromRetro(set: String){
        YugiRetrofitRequests().getYugiCardsList(object :Callback<YugiCardListRetroPojo>{
            override fun onResponse(
                call: Call<YugiCardListRetroPojo>,
                response: Response<YugiCardListRetroPojo>
            ) {
                if(response.isSuccessful && response.body()!!.data.cards.size > 0)
                    insertAllCardsFromSet(response.body()!!.data.cards, set)
            }

            override fun onFailure(call: Call<YugiCardListRetroPojo>, t: Throwable) {
                Toast.makeText(getApplication(), "Error, couldn't get cards list", Toast.LENGTH_LONG).show()
            }
        }, set)
    }

    fun getCardDataFromRetro(card:String){
        YugiRetrofitRequests().getYugiCardData(object :Callback<YugiCardDataRetroPojo>{
            override fun onResponse(
                call: Call<YugiCardDataRetroPojo>,
                response: Response<YugiCardDataRetroPojo>
            ) {
                if(response.isSuccessful && response.body()!!.data != null)
                    insertFavCard(response.body()!!.data)
            }

            override fun onFailure(call: Call<YugiCardDataRetroPojo>, t: Throwable) {
                Toast.makeText(getApplication(), "Error, couldn't get card data", Toast.LENGTH_LONG).show()
            }
        }, card)
    }

}