package com.example.cleanarcitecture.domain

interface CalculateUseCase {

    suspend fun calculate(first: Int, second: Int): Int

}