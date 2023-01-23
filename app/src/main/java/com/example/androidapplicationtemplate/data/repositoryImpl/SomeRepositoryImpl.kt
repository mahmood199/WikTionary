package com.example.androidapplicationtemplate.data.repositoryImpl

import com.example.androidapplicationtemplate.core.util.Resource
import com.example.androidapplicationtemplate.data.local.localDataSource.SomeLocalDataSource
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.SomeRemoteDataSource
import com.example.androidapplicationtemplate.domain.repository.SomeRepository
import javax.inject.Inject

class SomeRepositoryImpl @Inject constructor(
    localDataSource: SomeLocalDataSource,
    remoteDataSource: SomeRemoteDataSource,
) : SomeRepository {

    override suspend fun someCrudOperation(): Resource<String> {
        return Resource.Success("Sucess")
    }

}