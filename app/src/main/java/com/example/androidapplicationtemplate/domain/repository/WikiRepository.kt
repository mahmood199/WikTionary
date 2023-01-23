package com.example.androidapplicationtemplate.domain.repository

import com.example.androidapplicationtemplate.core.network.Resource

interface WikiRepository {
    suspend fun someCrudOperation(): Resource<String>
}