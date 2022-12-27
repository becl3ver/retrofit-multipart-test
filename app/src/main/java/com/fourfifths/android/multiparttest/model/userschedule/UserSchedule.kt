package com.fourfifths.android.multiparttest.model.userschedule

import com.google.gson.annotations.SerializedName

data class UserSchedule(
    @SerializedName("scheduleId")
    val scheduleId: Long,
    @SerializedName("scheduleName")
    val scheduleName: String,
    @SerializedName("scheduleDate")
    val scheduleDate: String,
    @SerializedName("status")
    val status: String
)