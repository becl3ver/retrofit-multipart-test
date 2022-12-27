package com.fourfifths.android.multiparttest.service

import com.fourfifths.android.multiparttest.model.userschedule.UserSchedule
import com.fourfifths.android.multiparttest.model.userschedule.UserScheduleCreateDto
import retrofit2.Response
import retrofit2.http.*

interface UserScheduleService {
    @POST("/api/v1/user/schedule")
    suspend fun createSchedule(
        @Header("AccessToken") accessToken: String,
        @Header("RefreshToken") refreshToken: String,
        @Body userScheduleCreateDto: UserScheduleCreateDto
    ): Response<ArrayList<UserSchedule>>
}