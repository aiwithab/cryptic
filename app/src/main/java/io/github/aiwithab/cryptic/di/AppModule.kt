package io.github.aiwithab.cryptic.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.aiwithab.cryptic.common.Constants
import io.github.aiwithab.cryptic.data.remote.CoinPaprikaApi
import io.github.aiwithab.cryptic.data.repository.CoinRepositoryImpl
import io.github.aiwithab.cryptic.domain.repository.CoinRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()

        okHttpClient.callTimeout(40, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(40, TimeUnit.SECONDS)
        okHttpClient.readTimeout(40, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(40, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }


    @Provides
    @Singleton
    fun providePaprikaApi(okHttpClient: OkHttpClient, baseUrl: String, converterFactory: Converter.Factory): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}