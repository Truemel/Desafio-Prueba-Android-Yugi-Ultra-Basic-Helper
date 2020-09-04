package com.example.yu_gi_ohultrabasichelper.modeloh.retrofit

data class YugiCardListRetroPojo (val data:CardListDataRetro)

data class CardListDataRetro(val cards:MutableList<CardsRetro>)

data class CardsRetro(val name:String)