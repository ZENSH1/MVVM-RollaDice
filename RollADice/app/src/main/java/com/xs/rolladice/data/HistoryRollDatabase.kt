package com.xs.rolladice.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xs.rolladice.data.HistoryRoll
import com.xs.rolladice.data.HistoryRollDao

@Database(
    entities = [HistoryRoll::class],
    version = 1
)
abstract class HistoryRollDatabase: RoomDatabase() {
    abstract val historyRollDao: HistoryRollDao
}