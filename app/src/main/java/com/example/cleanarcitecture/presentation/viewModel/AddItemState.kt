package com.example.cleanarcitecture.presentation.viewModel

sealed class AddItemState() {

    object Free : AddItemState()
    object Loading : AddItemState()
    object Result : AddItemState()
}
