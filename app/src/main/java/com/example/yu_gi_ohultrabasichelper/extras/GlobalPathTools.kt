package com.example.yu_gi_ohultrabasichelper.extras

const val PATH:String = "http://yugiohprices.com/api/"
const val IMAGES_PATH:String = "https://static-3.studiobebop.net/ygo_data/"

fun fixNameToPath(name:String):String{
    return "[:| ]".toRegex().replace(name.trim(), "_")
}