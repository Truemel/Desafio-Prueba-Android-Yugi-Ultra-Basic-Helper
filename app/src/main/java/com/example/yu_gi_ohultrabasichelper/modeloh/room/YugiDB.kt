package com.example.yu_gi_ohultrabasichelper.modeloh.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [YugiSetTablePojo::class, YugiCardTablePojo::class, YugiFavouriteTablePojo::class], version = 0, exportSchema = false)
abstract class YugiDB:RoomDatabase() {

    abstract fun getYugiDAO():YugiDAO

    companion object{
        private var db:YugiDB? = null
        private const val DB_NAME = "yugi_db"

        fun getDB(context: Context):YugiDB{
            if(db==null)
                synchronized(this){
                    db = Room.databaseBuilder(context, YugiDB::class.java, DB_NAME).build()
                }
            return db!!
        }
    }
}