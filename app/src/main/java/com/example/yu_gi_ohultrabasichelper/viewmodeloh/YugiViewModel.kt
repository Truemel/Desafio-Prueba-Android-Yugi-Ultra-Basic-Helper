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
    lateinit var yugiCardData:LiveData<YugiCardDataTablePojo>

    init {
        dbManager = YugiDBManager(YugiDB.getDB(application).getYugiDAO())
        yugiSetsList = dbManager.getSetsList()
        yugiFavList = dbManager.getFavCardsList()

        if(yugiSetsList.value == null || yugiSetsList.value!!.size == 0)
            getSetListFromRetro()
    }

    //Intenta conseguir la lista de cartas de un set especifico de la base de datos, si no lo encuentras la consigue del Api usando retrofit
    fun setCardList(set: String){
        if(!setCardsListToViewModel(set))
            getCardListFromSetFromRetro(set)
    }

    //Intenta conseguir los datos de una cartas especifica de la base de datos, si no los encuentra los consigue del Api usando retrofit
    fun setCardData(card: String){
        if(!setCardDataToViewModel(card))
            getCardDataFromRetro(card)
    }

    //Obtiene lista de cartas de la base de datos, retorna true si lo logra, de lo contrario retorna false
    fun setCardsListToViewModel(set:String):Boolean{
        var haveCards:Boolean = false

        yugiCardsList = dbManager.getCardsListFromSets(set)

        if(yugiCardsList.value != null && yugiCardsList.value!!.size > 0)
            haveCards = true

        return haveCards
    }

    //Obtiene los datos de una carta de la base de datos, retorna true si lo logra, de lo contrario retorna false
    fun setCardDataToViewModel(card:String):Boolean{
        var haveCard:Boolean = false

        yugiCardData = dbManager.getCardData(card)

        if(yugiCardData.value != null && yugiCardData.value!!.name.isNotEmpty())
            haveCard = true

        return haveCard
    }

    fun insertAllSets(setList:YugiSetsRetroPojo) = viewModelScope.launch { dbManager.insertAllSets(setList) }

    fun insertAllCardsFromSet(list:MutableList<CardsRetro>, set: String) = viewModelScope.launch { dbManager.insertAllCardsFromSet(list, set) }

    fun insertFavCard(card:CardDataRetro) = viewModelScope.launch { dbManager.insertFavCard(card) }

    fun insertCardData(card: CardDataRetro) = viewModelScope.launch { dbManager.insertCardData(card) }

    fun updateFavCard(card:YugiFavouriteTablePojo) = viewModelScope.launch { dbManager.updateFavCard(card) }

    fun deleteFavCard(card:YugiFavouriteTablePojo) = viewModelScope.launch { dbManager.deleteFavCard(card) }

    fun deleteAllFavCard() = viewModelScope.launch { dbManager.deleteAllFavCard() }

    //consigue la lista de sets del api usando retrofit
    fun getSetListFromRetro(){
        YugiRetrofitRequests().getYugiSetsList(object : Callback<MutableList<String>>{
            override fun onResponse(
                call: Call<MutableList<String>>,
                response: Response<MutableList<String>>
            ) {
                if(response.isSuccessful && response.body()!!.size > 0)
                    insertAllSets(YugiSetsRetroPojo(response.body()!!))
            }

            override fun onFailure(call: Call<MutableList<String>>, t: Throwable) {
                Toast.makeText(getApplication(), "Error, couldn't sets list", Toast.LENGTH_LONG).show()
            }
        })
    }

    //consigue la lista de cartas del api usando retrofit
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

    //consigue los datos de una carta del api usando retrofit
    fun getCardDataFromRetro(card:String){
        YugiRetrofitRequests().getYugiCardData(object :Callback<YugiCardDataRetroPojo>{
            override fun onResponse(
                call: Call<YugiCardDataRetroPojo>,
                response: Response<YugiCardDataRetroPojo>
            ) {
                if(response.isSuccessful)
                    insertCardData(response.body()!!.data)
            }

            override fun onFailure(call: Call<YugiCardDataRetroPojo>, t: Throwable) {
                Toast.makeText(getApplication(), "Error, couldn't get card data", Toast.LENGTH_LONG).show()
            }
        }, card)
    }

}