package com.example.cleanarcitecture.data

import com.example.cleanarcitecture.domain.CalculateRepository
import com.example.cleanarcitecture.domain.Operation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SumCalculator : CalculateRepository {

    override suspend fun calculate(operation: Operation): Int {
        withContext(Dispatchers.IO) {
            var sum = 0
            for (i in 0..Int.MAX_VALUE) {
                if (sum % 2 == 0) {
                    sum = +i
                } else {
                    sum -= i
                }
            }
        }
        return operation.first + operation.second
    }
}