package com.fourfifths.android.multiparttest.model.user

import com.google.gson.annotations.SerializedName

data class UserSignInResponseDto(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("emailAuthentication")
    val emailAuthentication: Boolean,
    @SerializedName("userStats")
    val userStats: Boolean,
    @SerializedName("userBlock")
    val userBlock: Boolean,
    @SerializedName("userPause")
    val userPause: Boolean,
    @SerializedName("pushStart")
    val pushStart: Boolean,
    @SerializedName("pushImminent")
    val pushImminent: Boolean,
    @SerializedName("pushDayAgo")
    val pushDayAgo: Boolean,
)
