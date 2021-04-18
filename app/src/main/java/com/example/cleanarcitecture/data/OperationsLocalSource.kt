package com.example.cleanarcitecture.data

import com.example.cleanarcitecture.domain.Operation
import com.example.cleanarcitecture.domain.OperationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class OperationsLocalSource : OperationsRepository {

    private var operations = mutableListOf<Operation>(Operation(1, 2, 3), Operation(3, 6, 9))

    override suspend fun getOperations(): MutableList<Operation> {
        withContext(Dispatchers.IO){
            delay(3000)
        }
        return operations
    }

    override suspend fun addOperation(operation: Operation) {
        operations.add(operation)
    }

    override suspend fun removeOperation(operation: Operation){
        operations.remove(operation)
    }


}