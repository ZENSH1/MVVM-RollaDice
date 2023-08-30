package com.xs.rolladice.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xs.rolladice.data.HistoryRoll
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryRollDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryRoll(historyRoll: HistoryRoll)

    @Delete
    suspend fun deleteHistoryRoll(historyRoll: HistoryRoll)

    @Query("SELECT * FROM historyroll")
     fun getHistoryRolls(): Flow<List<HistoryRoll>>

}