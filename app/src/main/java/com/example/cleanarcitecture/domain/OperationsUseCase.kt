package com.example.cleanarcitecture.domain

interface OperationsUseCase {
   suspend fun getOperations() : MutableList<Operation>
   suspend fun deleteOperation(operation: Operation)

}