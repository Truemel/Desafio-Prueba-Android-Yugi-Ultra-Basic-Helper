package com.example.yu_gi_ohultrabasichelper.modeloh.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sets_table")
data class YugiSetTablePojo(@PrimaryKey @NonNull val name:String)