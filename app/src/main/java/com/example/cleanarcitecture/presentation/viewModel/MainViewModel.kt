package com.example.cleanarcitecture.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarcitecture.Dependencies
import com.example.cleanarcitecture.domain.CalculateUseCase
import com.example.cleanarcitecture.domain.Operation
import com.example.cleanarcitecture.domain.OperationsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val calculateUseCase: CalculateUseCase by lazy { Dependencies.getCalculateUseCase() }
    private val operationsUseCase: OperationsUseCase by lazy { Dependencies.getOperationsUseCase() }
    var first: String = ""
    var second: String = ""

    private var operations = MutableLiveData<MutableList<Operation>>(mutableListOf())

    private var _calculationState = MutableLiveData<CalculationState>(CalculationState.Free)
    val calculationState: LiveData<CalculationState> = _calculationState

    fun getOperations(): LiveData<MutableList<Operation>> {
        return operations
    }


    fun calculate(): Int {
        var result: Int = 0
        _calculationState.value = CalculationState.Loading
        viewModelScope.launch {
            result = calculateUseCase.calculate(first.toInt(), second.toInt())
            operations.value = operationsUseCase.getOperations().toMutableList()
            _calculationState.value = CalculationState.Rezult
            setFree()
        }

        return result

    }

    init {
        viewModelScope.launch {
            operations.value = operationsUseCase.getOperations()
        }
    }

    suspend fun setFree() {
        delay(3000)
        _calculationState.value = CalculationState.Free
    }

fun onOperationSelected(operation: Operation){
    viewModelScope.launch {
        operationsUseCase.deleteOperation(operation)
        operations.value = operationsUseCase.getOperations()
    }
}
}