package wizeline.crypto.currency.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import wizeline.crypto.currency.data.resource.remote.CryptoCurrenciesApi
import wizeline.crypto.currency.utils.BASE_URL
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {


    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideUserAgent(): Interceptor=
        Interceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder().addHeader("User-Agent", "AARH").build()
            chain.proceed(newRequest)
        }

    @Provides
    @Singleton
    fun provideClient(
        interceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(headerInterceptor).build()

    @Provides
    @Singleton
    fun providersAvailableBooks(client:OkHttpClient): CryptoCurrenciesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoCurrenciesApi::class.java)

    }



}