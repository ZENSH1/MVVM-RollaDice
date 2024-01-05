package com.xs.rolladice.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xs.rolladice.data.local.dao.HistoryRollDao
import com.xs.rolladice.data.local.dao.NameDao
import com.xs.rolladice.data.local.model.HistoryRoll
import com.xs.rolladice.data.local.model.Name

@Database(entities = [Name::class, HistoryRoll::class],
    version = 3)
abstract class NameDatabase: RoomDatabase() {
    abstract val nameDao: NameDao
    abstract val historyRollDao: HistoryRollDao
}