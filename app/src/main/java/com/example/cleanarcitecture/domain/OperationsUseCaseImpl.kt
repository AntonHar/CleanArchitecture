package com.example.cleanarcitecture.domain

class OperationsUseCaseImpl(
    val operationsRepository: OperationsRepository
) : OperationsUseCase {
    override fun getOperations(): List<Operation> {
        return operationsRepository.getOperations()
    }
}