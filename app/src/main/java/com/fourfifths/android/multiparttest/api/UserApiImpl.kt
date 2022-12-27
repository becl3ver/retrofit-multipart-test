package com.fourfifths.android.multiparttest.api

import com.fourfifths.android.multiparttest.BuildConfig
import com.fourfifths.android.multiparttest.service.UserService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object UserApiImpl {
    private var service: UserService? = null

    fun getService(): UserService {
        if (service == null) {
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit: Retrofit =
                Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().setLenient().create()
                        )
                    ).build()

            service = retrofit.create(UserService::class.java)
        }

        return service!!
    }
}