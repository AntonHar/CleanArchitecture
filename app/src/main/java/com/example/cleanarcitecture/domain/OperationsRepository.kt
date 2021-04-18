package com.example.cleanarcitecture.domain

interface OperationsRepository {

    suspend fun getOperations(): MutableList<Operation>
    suspend fun addOperation(operation: Operation)
    suspend fun removeOperation(operation: Operation)
}