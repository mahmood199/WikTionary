package com.example.androidapplicationtemplate.data.repositoryImpl

import android.util.Log
import com.example.androidapplicationtemplate.core.util.FailureCode
import com.example.androidapplicationtemplate.core.util.FailureStatus
import com.example.androidapplicationtemplate.core.util.Resource
import com.example.androidapplicationtemplate.data.local.localDataSource.WikiLocalDataSource
import com.example.androidapplicationtemplate.data.models.response.Continue
import com.example.androidapplicationtemplate.data.models.response.Query
import com.example.androidapplicationtemplate.data.models.response.Response
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.WikiRemoteDataSource
import com.example.androidapplicationtemplate.domain.repository.WikiRepository
import javax.inject.Inject

class WikiRepositoryImpl @Inject constructor(
    val localDataSource: WikiLocalDataSource,
    val remoteDataSource: WikiRemoteDataSource,
) : WikiRepository {

    override suspend fun getWikis(searchQuery: String): Resource<Response> {
        return try {
            val result = remoteDataSource.get(searchQuery)
            val localResult = localDataSource.addAll(result)
            localResult.forEach {
                Log.d("WikiRepositoryImpl0", "$it")
            }
            if(result.query.pages.size == localResult.size) {
                Log.d("WikiRepositoryImpl1", " ${localResult.size} records inserted successfully")
                val localResult = localDataSource.getAll(searchQuery)
                localResult.forEach { it ->
                    Log.d("WikiRepositoryImpl1", " $it")
                }
            }
            else
                Log.d("WikiRepositoryImpl2", " ${localResult.size} insertion unsuccessful")
            Resource.Success(result)
        } catch (exception: Exception) {
            val result = localDataSource.getAll(searchQuery)
            if(result.isEmpty()) {
                Log.d("WikiRepositoryImpl3", " ${result.size} records fetched")
                Resource.Failure(FailureStatus.API_FAIL, FailureCode.RESOURCE_NOT_FOUND)
            }
            else {
                Log.d("WikiRepositoryImp4", " ${result.size} records inserted successfully")
                Resource.Success(Response(false, Continue(), Query(pages = result), true))
            }
        }
    }

}