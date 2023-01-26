package com.example.androidapplicationtemplate.data.remote.remoteServices

import com.example.androidapplicationtemplate.data.models.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiService {

    companion object {
        const val SEARCH_QUERY = "titles"
        const val LIMIT = "pilimit"
    }

    @GET("/w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&formatversion=2")
    suspend fun getWiki(
        @Query(SEARCH_QUERY) searchQuery: String,
        @Query(LIMIT) limit: Int,
    ): Response

}