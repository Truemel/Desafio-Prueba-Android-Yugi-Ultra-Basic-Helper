package com.example.yu_gi_ohultrabasichelper.modeloh.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class YugiCardTablePojo(@PrimaryKey @NonNull val name:String, val set:String)