package com.example.cleanarcitecture

import com.example.cleanarcitecture.data.db.LocalDatabaseSource
import com.example.cleanarcitecture.domain.usecase.PersonUseCase
import com.example.cleanarcitecture.domain.usecase.PersonsUseCaseImpl

object Dependencies {

    private val localDatabaseSource: LocalDatabaseSource by lazy { LocalDatabaseSource(App.instance) }

    fun getPersonsUseCase(): PersonUseCase =
        PersonsUseCaseImpl(localDatabaseSource)


}