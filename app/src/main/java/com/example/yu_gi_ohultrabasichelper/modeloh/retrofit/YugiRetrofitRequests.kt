package com.example.yu_gi_ohultrabasichelper.modeloh.retrofit

import retrofit2.Call
import retrofit2.Callback

class YugiRetrofitRequests {

    private var onRetroListener:YugiRetrofitInterface? = null

    init {
        onRetroListener = YugiRetrofitClient.getRetro().create(YugiRetrofitInterface::class.java)
    }

    fun getYugiSetsList(callback: Callback<MutableList<String>>){
        val call:Call<MutableList<String>> = onRetroListener!!.getYugiSetList()
        call.enqueue(callback)
    }

    fun getYugiCardsList(callback: Callback<YugiCardListRetroPojo>, setName:String){
        val call:Call<YugiCardListRetroPojo> = onRetroListener!!.getYugiCardsFromSet(setName)
        call.enqueue(callback)
    }

    fun getYugiCardData(callback: Callback<YugiCardDataRetroPojo>, cardName:String){
        val call:Call<YugiCardDataRetroPojo> = onRetroListener!!.getYugiCardData(cardName)
        call.enqueue(callback)
    }
}