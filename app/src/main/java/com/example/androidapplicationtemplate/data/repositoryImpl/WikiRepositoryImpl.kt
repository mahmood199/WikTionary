package com.example.androidapplicationtemplate.data.repositoryImpl

import com.example.androidapplicationtemplate.core.util.FailureCode
import com.example.androidapplicationtemplate.core.util.FailureStatus
import com.example.androidapplicationtemplate.core.util.Resource
import com.example.androidapplicationtemplate.data.local.localDataSource.WikiLocalDataSource
import com.example.androidapplicationtemplate.data.models.response.Response
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.WikiRemoteDataSource
import com.example.androidapplicationtemplate.domain.repository.WikiRepository
import javax.inject.Inject

class WikiRepositoryImpl @Inject constructor(
    val localDataSource: WikiLocalDataSource,
    val remoteDataSource: WikiRemoteDataSource,
) : WikiRepository {

    override suspend fun someCrudOperation(): Resource<Response> {
        return try {
            val result = remoteDataSource.get()
            Resource.Success(result)
        } catch (exception: Exception) {
            Resource.Failure(FailureStatus.API_FAIL, FailureCode.RESOURCE_NOT_FOUND)
        }
    }

}