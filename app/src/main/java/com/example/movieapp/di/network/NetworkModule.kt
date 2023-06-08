package com.example.movieapp.di.network

import com.example.movieapp.BuildConfig
import com.example.movieapp.network.ApiInterface
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
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NWY5ZmYzMzhlNGY4MTc2Y2Q1OTM3MDZiODJmNTAxMSIsInN1YiI6IjVkYzk1ZmQ3NDcwZWFkMDAxMzk4N2ZhOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4q29og1uwwWqxFp63WK2W3qM8Wh0ZOq_N_waEmhkh2U"
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
        return Retrofit.Builder().client(client).baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}