package com.example.yu_gi_ohultrabasichelper.modeloh.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class YugiFavouriteTablePojo(@PrimaryKey @NonNull val name:String, val text:String, val card_type:String, val type:String, val family:String, val atk:Int, val def:Int, val level:Short, val property:String?)