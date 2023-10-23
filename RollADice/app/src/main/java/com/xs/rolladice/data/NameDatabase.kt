package com.xs.rolladice.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Name::class, HistoryRoll::class],
    version = 3)
abstract class NameDatabase: RoomDatabase() {
    abstract val nameDao: NameDao
    abstract val historyRollDao: HistoryRollDao
}