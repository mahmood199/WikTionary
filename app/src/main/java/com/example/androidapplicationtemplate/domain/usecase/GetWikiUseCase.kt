package com.example.androidapplicationtemplate.domain.usecase

import com.example.androidapplicationtemplate.core.util.Resource
import com.example.androidapplicationtemplate.data.models.response.Response
import com.example.androidapplicationtemplate.domain.repository.WikiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetWikiUseCase @Inject constructor(
    private val wikiRepository: WikiRepository,
) {

    suspend operator fun invoke(searchQuery: String): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)
        val result = wikiRepository.getWikis(searchQuery)
        emit(result)
    }.flowOn(Dispatchers.IO)


}