package com.example.androidapplicationtemplate.domain.repository

import com.example.androidapplicationtemplate.core.util.Resource

interface SomeRepository {
    suspend fun someCrudOperation(): Resource<String>
}