package com.example.cleanarcitecture.presentation.adapter

import com.example.cleanarcitecture.domain.entity.Person

interface ItemClickListener {
    fun onItemClick(person: Person)
}