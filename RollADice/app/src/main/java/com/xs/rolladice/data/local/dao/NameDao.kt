package com.xs.rolladice.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xs.rolladice.data.local.model.Name
import kotlinx.coroutines.flow.Flow

@Dao
interface NameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertName(name: Name)

    @Delete
    suspend fun deleteName(name: Name)

    @Query("SELECT * FROM name WHERE id = :id")
    suspend fun getNameById(id: Int): Name?

    @Query("SELECT * FROM name")
     fun getNames(): Flow<List<Name>>
}