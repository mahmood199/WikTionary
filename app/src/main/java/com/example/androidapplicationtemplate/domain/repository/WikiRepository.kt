package com.example.androidapplicationtemplate.domain.repository

import com.example.androidapplicationtemplate.core.util.Resource
import com.example.androidapplicationtemplate.data.models.response.Response

interface WikiRepository {
    suspend fun getWikis(searchQuery: String): Resource<Response>
}