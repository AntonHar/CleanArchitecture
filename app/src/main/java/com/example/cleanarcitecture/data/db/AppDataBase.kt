package com.example.cleanarcitecture.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarcitecture.data.db.dao.PersonDao
import com.example.cleanarcitecture.domain.entity.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDataBase:RoomDatabase() {
    abstract fun getPersonDao(): PersonDao
}