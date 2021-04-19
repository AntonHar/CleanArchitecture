package com.example.cleanarcitecture.data.db.dao

import androidx.room.*
import com.example.cleanarcitecture.domain.entity.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Query("SELECT * FROM Person")
    fun selectAll(): List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: Person)

    @Delete
    fun delete(person: Person)
}