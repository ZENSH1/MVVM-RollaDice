package com.xs.rolladice.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryRollDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryRoll(historyRoll: HistoryRoll)

    @Delete
    suspend fun deleteHistoryRoll(historyRoll: HistoryRoll)

    @Query("SELECT * FROM historyroll WHERE name = :name")
     fun getHistoryRolls(name:String): Flow<List<HistoryRoll>>
}