package com.xs.rolladice.repository

import com.xs.rolladice.data.local.model.Name
import kotlinx.coroutines.flow.Flow

interface NameRepository {

    suspend fun insertName(name: Name)


    suspend fun deleteName(name: Name)

    suspend fun getNameById(id: Int): Name?

     fun getNames(): Flow<List<Name>>

}