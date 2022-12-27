package com.fourfifths.android.multiparttest.service

import com.fourfifths.android.multiparttest.model.user.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    @Multipart
    @POST("/api/v1/signup")
    suspend fun signUp(
        @Part param: MultipartBody.Part, @Part file: MultipartBody.Part
    ): Response<String>

    @GET("/api/v1/signup/email/auth")
    suspend fun checkEmailAuth(
        @Header("AccessToken") accessToken: String, @Header("RefreshToken") refreshToken: String
    ): Response<Boolean>

    @GET("/api/v1/user/password")
    suspend fun reissueTemporaryPassword(
        @Query("userId") userId: String
    ): Response<Boolean>

    @Multipart
    @POST("/api/v1/signin")
    suspend fun signIn(
        @Part(value = "userId", encoding = "8-bit") userId: RequestBody,
        @Part(value = "password", encoding = "8-bit") password: RequestBody
    ): Response<UserSignInResponseDto>
}