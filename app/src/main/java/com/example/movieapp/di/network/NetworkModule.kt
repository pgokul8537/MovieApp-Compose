package com.example.movieapp.di.network

import com.example.movieapp.network.ApiInterface
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.Constants.access_token
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson().newBuilder().setPrettyPrinting().create()


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        clientBuilder.addInterceptor {
            val authorizationToken =
                access_token
            val original = it.request()
            var request: Request?
            request = original.newBuilder()
                .header("Authorization", authorizationToken)
                .build()
            it.proceed(request)

        }
        clientBuilder.addInterceptor(interceptor)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(Constants.base_url)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}