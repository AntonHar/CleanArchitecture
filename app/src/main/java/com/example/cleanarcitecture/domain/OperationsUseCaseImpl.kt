package com.example.cleanarcitecture.domain

class OperationsUseCaseImpl(
    private val operationsRepository: OperationsRepository
) : OperationsUseCase {
    override suspend fun getOperations(): MutableList<Operation> {
        return operationsRepository.getOperations()
    }

    override suspend fun deleteOperation(operation: Operation) {
        operationsRepository.removeOperation(operation)
    }
}