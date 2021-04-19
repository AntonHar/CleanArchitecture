package com.example.cleanarcitecture.domain.usecase

import com.example.cleanarcitecture.domain.entity.Person
import kotlinx.coroutines.flow.Flow

class PersonsUseCaseImpl(private val personsRepository: PersonsRepository): PersonUseCase {
    override suspend fun getPersons(): Flow<List<Person>> {
        return personsRepository.getPersons()
    }

    override suspend fun removePerson(person: Person) {
        personsRepository.removePerson(person)
    }

    override suspend fun registerPerson(name: String, rate: Int) {
        val person = Person(name,rate)
        personsRepository.addPerson(person)
    }
}