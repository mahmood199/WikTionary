package com.example.androidapplicationtemplate.domain.repository

import com.example.androidapplicationtemplate.data.models.response.Response

interface WikiRepository {
    suspend fun someCrudOperation(): List<Response>
}