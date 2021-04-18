package com.example.cleanarcitecture.domain

interface OperationsUseCase {
    fun getOperations() : List<Operation>
}