package com.example.yu_gi_ohultrabasichelper.modeloh.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface YugiRetrofitInterface {

    @GET("card_sets")
    fun getYugiSetList():Call<MutableList<String>>

    @GET("set_data/{set_name}")
    fun getYugiCardsFromSet(@Path("set_name") setName:String):Call<YugiCardListRetroPojo>

    @GET("card_data/{card_name}")
    fun getYugiCardData(@Path("card_name") cardName:String):Call<YugiCardDataRetroPojo>
}