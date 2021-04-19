package com.example.cleanarcitecture.data.db

import android.content.Context
import androidx.room.Room
import com.example.cleanarcitecture.domain.entity.Person
import com.example.cleanarcitecture.domain.usecase.PersonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext

class LocalDatabaseSource(context: Context) : PersonsRepository {
    private val db = Room.databaseBuilder(
        context,
        AppDataBase::class.java, "personDataBase"
    )
        .allowMainThreadQueries().build()
    private val personsFlow: MutableSharedFlow<List<Person>> = MutableSharedFlow(replay = 1)

    override suspend fun getPersons(): Flow<List<Person>> = personsFlow
        .apply{
            emit(db.getPersonDao().selectAll())
        }

    override suspend fun addPerson(person: Person) {
        withContext(Dispatchers.IO){
            delay(2000)
        }
        db.getPersonDao().insert(person)
        personsFlow.emit(db.getPersonDao().selectAll())
    }

    override suspend fun removePerson(person: Person){
        withContext(Dispatchers.IO){
            delay(2000)
        }
        db.getPersonDao().delete(person)
        personsFlow.emit(db.getPersonDao().selectAll())
    }
}