package com.example.cleanarcitecture.domain

interface CalculateRepository {

    suspend fun calculate(operation: Operation): Int

}