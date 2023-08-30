package com.xs.rolladice.repository

import com.xs.rolladice.data.HistoryRoll
import com.xs.rolladice.data.HistoryRollDao
import kotlinx.coroutines.flow.Flow

class HistoryRollRepositoryImpl(
    private val historyRollDao: HistoryRollDao
) : HistoryRollRepository {
    override suspend fun insertHistoryRoll(historyRoll: HistoryRoll) {
        historyRollDao.insertHistoryRoll(historyRoll)
    }

    override suspend fun deleteHistoryRoll(historyRoll: HistoryRoll) {
        historyRollDao.deleteHistoryRoll(historyRoll)
    }

    override fun getHistoryRolls(): Flow<List<HistoryRoll>> {
        return historyRollDao.getHistoryRolls()
    }

}