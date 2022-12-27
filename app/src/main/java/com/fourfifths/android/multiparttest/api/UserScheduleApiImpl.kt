package com.fourfifths.android.multiparttest.api

import com.fourfifths.android.multiparttest.BuildConfig
import com.fourfifths.android.multiparttest.service.UserScheduleService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserScheduleApiImpl {
    private var service: UserScheduleService? = null

    fun getService(): UserScheduleService {
        if (service == null) {
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit: Retrofit =
                Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(client)
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().setLenient().create()
                        )
                    ).build()

            service = retrofit.create(UserScheduleService::class.java)
        }

        return service!!
    }
}