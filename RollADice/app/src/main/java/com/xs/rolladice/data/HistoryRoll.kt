package com.xs.rolladice.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryRoll(
    val name: String,
    val roll: Int,
    val date: String,
    @PrimaryKey val id: Int? = null,
)


