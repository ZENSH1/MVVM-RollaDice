package com.xs.rolladice.repository

import com.xs.rolladice.data.local.model.HistoryRoll
import kotlinx.coroutines.flow.Flow

interface HistoryRollRepository {


    suspend fun insertHistoryRoll(historyRoll: HistoryRoll)


    suspend fun deleteHistoryRoll(historyRoll: HistoryRoll)

     fun getHistoryRolls(name:String): Flow<List<HistoryRoll>>
}