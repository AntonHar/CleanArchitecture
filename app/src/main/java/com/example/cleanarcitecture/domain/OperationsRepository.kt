package com.example.cleanarcitecture.domain

interface OperationsRepository {

    fun getOperations(): List<Operation>
    fun addOperation(operation: Operation)
}