package com.example.androidapplicationtemplate.data.repositoryImpl

import com.example.androidapplicationtemplate.data.local.localDataSource.WikiLocalDataSource
import com.example.androidapplicationtemplate.data.models.response.Response
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.WikiRemoteDataSource
import com.example.androidapplicationtemplate.domain.repository.WikiRepository
import javax.inject.Inject

class WikiRepositoryImpl @Inject constructor(
    val localDataSource: WikiLocalDataSource,
    val remoteDataSource: WikiRemoteDataSource,
) : WikiRepository {

    override suspend fun someCrudOperation(): List<Response> {
        return remoteDataSource.get()
    }

}