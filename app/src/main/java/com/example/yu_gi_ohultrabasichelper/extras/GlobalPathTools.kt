package com.example.yu_gi_ohultrabasichelper.extras

const val PATH:String = "http://yugiohprices.com/api/"
const val IMAGES_PATH:String = "https://static-3.studiobebop.net/ygo_data/"
const val IMAGES_SETS:String = IMAGES_PATH+"set_images/"
const val IMAGES_CARDS:String = IMAGES_PATH+"card_images/"
const val JPG:String = ".jpg"

fun fixNameToPath(name:String):String{
    return "[: -]".toRegex().replace(name.trim(), "_")
}