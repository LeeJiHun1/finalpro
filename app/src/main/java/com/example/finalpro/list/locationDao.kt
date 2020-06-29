package com.example.finalpro.list

import android.content.Context
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface locationDao{

    @Insert
    fun insertLocation(Location:List<Location>)

    @Insert
    fun insertSave(saveItem: SaveItem):Long

    @Query("SELECT * FROM SaveItem")
    fun loadSaveItems():DataSource.Factory<Int, SaveItem>

    @Query("SELECT * FROM locations WHERE saveId = :saveId")
    fun loadlocation(saveId: Long): DataSource.Factory<Int, Location>

    @Query("DELETE FROM SaveItem WHERE id = :saveId")
    fun deleteSaveData(saveId: Long)

}

