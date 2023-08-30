package com.xs.rolladice.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Name::class],
    version = 1)
abstract class NameDatabase: RoomDatabase() {
    abstract val nameDao: NameDao
}