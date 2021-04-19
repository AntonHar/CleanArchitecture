package com.example.cleanarcitecture.domain.usecase

import com.example.cleanarcitecture.domain.entity.Person
import kotlinx.coroutines.flow.Flow

interface PersonUseCase {
    suspend fun getPersons():Flow<List<Person>>
    suspend fun removePerson(person: Person)
    suspend fun registerPerson(name: String, rate: Int)
}