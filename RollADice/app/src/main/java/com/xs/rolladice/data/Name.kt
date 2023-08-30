package com.xs.rolladice.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Name(
    var name: String,
    @PrimaryKey val id: Int? = null,
)
