package com.example.yu_gi_ohultrabasichelper.modeloh.retrofit

import com.example.yu_gi_ohultrabasichelper.extras.PATH
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YugiRetrofitClient {

    companion object{
        private var retro: Retrofit? = null

        fun getRetro():Retrofit{
            if(retro == null)
                synchronized(this){
                    retro = Retrofit.Builder().baseUrl(PATH).addConverterFactory(GsonConverterFactory.create()).build()
                }
            return retro!!
        }
    }
}