package ua.vmartyniuk.githubpetapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.vmartyniuk.githubpetapp.BuildConfig
import ua.vmartyniuk.githubpetapp.data.network.config.ResultAdapterFactory
import ua.vmartyniuk.githubpetapp.data.network.service.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideConverter(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRetrofit(converterFactory: Converter.Factory) =
        Retrofit.Builder()
            .baseUrl(BuildConfig.GITHUB_BASE_URL)
            .addCallAdapterFactory(ResultAdapterFactory())
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}