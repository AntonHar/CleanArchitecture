package com.example.cleanarcitecture.domain.usecase

import com.example.cleanarcitecture.domain.entity.Person
import kotlinx.coroutines.flow.Flow

interface PersonsRepository {
    suspend fun addPerson(person: Person)
    suspend fun getPersons():Flow<List<Person>>
    suspend fun removePerson(person: Person)
}