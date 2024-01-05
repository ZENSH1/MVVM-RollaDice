package com.xs.rolladice.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Name(
    var name: String,
    @PrimaryKey val id: Int? = null,
)
