package com.example.cleanarcitecture.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarcitecture.Dependencies
import com.example.cleanarcitecture.domain.entity.Person
import com.example.cleanarcitecture.domain.usecase.PersonUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val personUseCase: PersonUseCase by lazy { Dependencies.getPersonsUseCase() }
    var name: String = ""
    var rate: Int = 0
    private var persons = MutableLiveData<List<Person>>(listOf())

    private var _calculationState = MutableLiveData<AddItemState>(AddItemState.Free)
    val addItemState: LiveData<AddItemState> = _calculationState

    fun getPersons(): LiveData<List<Person>> {
        return persons
    }

    fun registerPerson() {
        _calculationState.value = AddItemState.Loading
        viewModelScope.launch {
            personUseCase.registerPerson(name, rate)
            _calculationState.value = AddItemState.Result
            setFree()
        }
    }

    init {
        viewModelScope.launch {
            personUseCase.getPersons().collect {
                persons.value = it
            }
        }
    }

    suspend fun setFree() {
        delay(2000)
        _calculationState.value = AddItemState.Free
    }

    fun onOperationSelected(person: Person) {
        viewModelScope.launch {
            personUseCase.removePerson(person)
        }
    }
}