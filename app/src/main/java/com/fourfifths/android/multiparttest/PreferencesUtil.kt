package com.fourfifths.android.multiparttest

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class PreferencesUtils(context: Context) {
    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "PreferenceUtils",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun setAccessToken(token: String) {
        prefs.edit().putString("accessToken", "Bearer $token").apply()
    }

    fun getAccessToken(): String {
        return prefs.getString("accessToken", "")!!
    }

    fun setRefreshToken(token: String) {
        prefs.edit().putString("refreshToken", "Bearer $token").apply()
    }

    fun getRefreshToken(): String {
        return prefs.getString("refreshToken", "")!!
    }
}