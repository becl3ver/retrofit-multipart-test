package com.fourfifths.android.multiparttest.model.user

import com.google.gson.annotations.SerializedName

data class Study(
    @SerializedName("studyId")
    val studyId: Long,
    @SerializedName("studyName")
    val studyName: String,
    @SerializedName("studyCategory")
    val studyCategory: String,
    @SerializedName("studyRule")
    val studyRule: HashMap<String, HashMap<String, Int>>,
    @SerializedName("studyOn")
    val studyOn: Boolean,
    @SerializedName("studyOff")
    val studyOff: Boolean,
    @SerializedName("studyInfo")
    val studyInfo: String,
    @SerializedName("studyBlock")
    val studyBlock: Boolean,
    @SerializedName("studyPause")
    val studyPause: Boolean
)
