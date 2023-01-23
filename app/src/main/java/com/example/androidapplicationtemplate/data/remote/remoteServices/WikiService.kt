package com.example.androidapplicationtemplate.data.remote.remoteServices

import com.example.androidapplicationtemplate.data.models.request.Request
import com.example.androidapplicationtemplate.data.models.response.Response
import retrofit2.http.GET

interface WikiService {

	@GET("/w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpssearch=Sachin+T&gpslimit=10")
	suspend fun getWiki() : List<Response>

}