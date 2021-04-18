package com.example.cleanarcitecture.data

import com.example.cleanarcitecture.domain.Operation
import com.example.cleanarcitecture.domain.OperationsRepository

class OperationsLocalSource : OperationsRepository {

    private var operations = mutableListOf<Operation>(Operation(1, 2, 3), Operation(3, 6, 9))

    override fun getOperations(): List<Operation> {
        return operations
    }

    override fun addOperation(operation: Operation) {
        operations.add(operation)
    }


}