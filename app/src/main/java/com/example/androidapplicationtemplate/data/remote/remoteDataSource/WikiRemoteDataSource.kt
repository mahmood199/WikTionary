package com.example.androidapplicationtemplate.data.remote.remoteDataSource

import com.example.androidapplicationtemplate.data.models.response.Response
import com.example.androidapplicationtemplate.data.remote.remoteServices.WikiService
import javax.inject.Inject

class WikiRemoteDataSource @Inject constructor(
	private val wikiService: WikiService
) {

	companion object {
		const val LIMIT = 20
		const val GPS_LIMIT = 20
	}

	suspend fun get(searchQuery: String): Response {
		return wikiService.getWiki(searchQuery, LIMIT, GPS_LIMIT)
	}

}