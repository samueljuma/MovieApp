package com.samueljuma.movieapp.presentation.di

import com.samueljuma.movieapp.BuildConfig
import com.samueljuma.movieapp.data.api.TMDBService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // The @InstallIn annotation in Hilt defines which Android class a module will be installed in.
class NetworkModule {

    @Provides
    open fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi{
        return Moshi.Builder()
//            .add(KotlinJsonAdapterFactory()) Not needed
                /*
                * since we are using @JsonClass(generateAdapter = true) on our data classes, we are using code generation
                * for creating JSON adapters. In this case, the KotlinJsonAdapterFactory
                * is not required because the adapters are already generated
                * by the annotation processor at compile time.
                 */

            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): TMDBService{
        return retrofit.create(TMDBService::class.java)
    }
}