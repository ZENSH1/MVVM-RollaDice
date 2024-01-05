package com.xs.rolladice.repository

import com.xs.rolladice.data.local.model.Name
import com.xs.rolladice.data.local.dao.NameDao
import kotlinx.coroutines.flow.Flow

class NameRepositoryImpl(private val nameDao: NameDao
): NameRepository {


    override suspend fun insertName(name: Name) {
        nameDao.insertName(name)
    }

    override suspend fun deleteName(name: Name) {
       nameDao.deleteName(name)
    }

    override suspend fun getNameById(id: Int): Name? {
        return nameDao.getNameById(id)
    }

    override fun getNames(): Flow<List<Name>> {
        return nameDao.getNames()
    }


}