package com.example.androidapplicationtemplate.data.remote.remoteServices

import com.example.androidapplicationtemplate.data.models.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiService {

    companion object {
        const val SEARCH_QUERY = "titles"
        const val LIMIT = "pilimit"
        const val GPS_SEARCH = "gpssearch"
        const val GPS_LIMIT = "gpslimit"
        const val REDIRECT = "redirects"
        const val THUMBNAIL_SIZE = "pithumbsize"
    }

    @GET("/w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&wbptterms=description")
    suspend fun getWiki(
        @Query(GPS_SEARCH) searchQuery: String,
        @Query(LIMIT) limit: Int,
        @Query(GPS_LIMIT) gpsLimit: Int,
    ): Response

}