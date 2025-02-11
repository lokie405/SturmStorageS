package com.seryoga.sturmstorages.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Product::class],
    version = 1
)
abstract class SturmDB : RoomDatabase() {
    abstract fun dao(): Dao

    companion object {
        private var INSTANCE: SturmDB? = null
        fun getInstance(context: Context): SturmDB {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SturmDB::class.java,
                        "sturm_storage.db"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}