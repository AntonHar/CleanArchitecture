package com.example.cleanarcitecture.data

import com.example.cleanarcitecture.domain.CalculateRepository
import com.example.cleanarcitecture.domain.Operation

class SubstractCalculator : CalculateRepository {
    override suspend fun calculate(operation: Operation): Int {
        return operation.first - operation.second
    }
}