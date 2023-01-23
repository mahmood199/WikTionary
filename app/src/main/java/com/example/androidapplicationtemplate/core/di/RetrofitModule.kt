package com.example.androidapplicationtemplate.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

	private lateinit var retrofit: Retrofit
	private const val BASE_URL = "https://en.wikipedia.org"

	@Provides
	fun getClient(
		okHttpClient: OkHttpClient
	): Retrofit {
		return if (this::retrofit.isInitialized)
			retrofit
		else {
			retrofit = Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(okHttpClient)
				.baseUrl(BASE_URL)
				.build()
			retrofit
		}
	}

	@Provides
	fun getOkhttpClient(
		httpLoggingInterceptor: HttpLoggingInterceptor
	): OkHttpClient = OkHttpClient.Builder()
		.addNetworkInterceptor(httpLoggingInterceptor)
		.build()

	@Provides
	fun getHTTPLoggingInterceptor(): HttpLoggingInterceptor {
		val logging = HttpLoggingInterceptor()
		logging.level = HttpLoggingInterceptor.Level.BODY
		return logging
	}


}