package com.example.yu_gi_ohultrabasichelper.extras

const val PATH:String = "http://yugiohprices.com/api/"
const val IMAGES_PATH:String = "https://static-3.studiobebop.net/ygo_data/"
const val IMAGES_SETS:String = IMAGES_PATH+"set_images/"
const val IMAGES_CARDS:String = IMAGES_PATH+"card_images/"
const val JPG:String = ".jpg"

//Funcion para cambiar los caracteres ':', ' ' y '-' por el caracter '_'
fun fixNameToPath(name:String):String{
    return "[: -]".toRegex().replace(name.trim(), "_")
}

//Funcion para armar el enlace a las imagenes de los Sets
fun getSetImagePath(name:String):String{
    return IMAGES_SETS+fixNameToPath(name)+JPG
}

//Funcion para armar el enlace a las imagenes de las Cartas
fun getCardImagePath(name:String):String{
    return IMAGES_CARDS+fixNameToPath(name)+JPG
}