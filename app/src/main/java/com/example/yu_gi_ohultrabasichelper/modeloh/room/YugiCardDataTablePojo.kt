package com.example.yu_gi_ohultrabasichelper.modeloh.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_data_table")
data class YugiCardDataTablePojo(@PrimaryKey @NonNull val name:String, val text:String, val card_type:String, val type:String, val family:String, val atk:Int, val def:Int, val level:Short, val property:String?)