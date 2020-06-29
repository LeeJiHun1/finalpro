package com.example.finalpro.DatabaseModule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finalpro.list.Location
import com.example.finalpro.list.locationDao
import com.example.finalpro.list.SaveItem

@Database(entities = [Location::class, SaveItem::class], version = 1)
abstract class DatabaseModule : RoomDatabase(){

    abstract fun freshDao(): locationDao

    companion object {
        private var database: DatabaseModule? = null

        private const val ROOM_DB = "room.db"

        fun getDatabase(context:Context):DatabaseModule{
            if(database ==null){
                database = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseModule::class.java, ROOM_DB
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return database!!
        }
    }
}