package com.example.cleanarcitecture

import com.example.cleanarcitecture.data.OperationsLocalSource
import com.example.cleanarcitecture.data.SumCalculator
import com.example.cleanarcitecture.domain.*

object Dependencies {

    private val operationsRepository: OperationsRepository by lazy { OperationsLocalSource() }

    fun getCalculateRepository(): CalculateRepository {
        return SumCalculator()
    }

    fun getOperaationsRepository():OperationsRepository{
        return operationsRepository
    }

    fun getCalculateUseCase(): CalculateUseCase {
        return CalculateUseCaseImplementation(getCalculateRepository(), getOperaationsRepository())
    }

    fun getOperationsUseCase(): OperationsUseCase {
        return OperationsUseCaseImpl(getOperaationsRepository())
    }

}