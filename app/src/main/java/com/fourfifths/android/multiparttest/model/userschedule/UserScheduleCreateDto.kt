package com.fourfifths.android.multiparttest.model.userschedule

import com.google.gson.annotations.SerializedName

data class UserScheduleCreateDto(
    @SerializedName("scheduleName")
    val scheduleName: String,
    @SerializedName("scheduleDate")
    val scheduleDate: String
)
