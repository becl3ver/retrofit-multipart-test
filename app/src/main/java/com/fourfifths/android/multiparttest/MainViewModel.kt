package com.fourfifths.android.multiparttest

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fourfifths.android.multiparttest.api.UserApiImpl
import com.fourfifths.android.multiparttest.api.UserScheduleApiImpl
import com.fourfifths.android.multiparttest.model.user.UserSignInResponseDto
import com.fourfifths.android.multiparttest.model.userschedule.UserScheduleCreateDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class MainViewModel : ViewModel() {
    private val TAG = this.javaClass.simpleName

    private val _uri = MutableLiveData<Uri?>(null)
    val uri: LiveData<Uri?> get() = _uri

    fun setUri(uri: Uri?) {
        _uri.value = uri
    }

    private val _accessToken = MutableLiveData("")
    val accessToken: LiveData<String> get() = _accessToken

    private val _refreshToken = MutableLiveData("")
    val refreshToken: LiveData<String> get() = _refreshToken

    private val _emailCheckResult = MutableLiveData(false)
    val emailCheckResult: LiveData<Boolean> get() = _emailCheckResult

    private val _loginResult = MutableLiveData<UserSignInResponseDto?>(null)
    val loginResult: LiveData<UserSignInResponseDto?> get() = _loginResult

    fun signUp(contentResolver: ContentResolver) {
        CoroutineScope(Dispatchers.IO).launch {
            val jsonObject = JSONObject()

            jsonObject.put("userId", "choijaehoon.dev@gmail.com")
            jsonObject.put("password", "abc123123!@#")
            jsonObject.put("password_check", "abc123123!@#")
            jsonObject.put("nickname", "최재훈")

            val param = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
            val file = InputStreamRequestBody(
                "application/octet-stream".toMediaTypeOrNull()!!, contentResolver, uri.value
            )

            val paramPart = MultipartBody.Part.createFormData("param", "param", param)
            val filePart = MultipartBody.Part.createFormData("file", "file", file)

            val response = UserApiImpl.getService().signUp(paramPart, filePart)

            if (response.isSuccessful) {
                _accessToken.postValue(response.headers()["AccessToken"]!!)
                _refreshToken.postValue(response.headers()["RefreshToken"]!!)

                App.pref.setAccessToken(_accessToken.value!!)
                App.pref.setRefreshToken(_refreshToken.value!!)
            } else {
                Log.e(TAG, response.code().toString())
                Log.e(TAG, response.message().toString())
            }
        }
    }

    fun completeEmailCheck() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = UserApiImpl.getService()
                .checkEmailAuth(App.pref.getAccessToken(), App.pref.getRefreshToken())

            if (response.isSuccessful && response.body() as Boolean) {
                _emailCheckResult.postValue(true)
            } else {
                Log.e(TAG, response.code().toString())
                Log.e(TAG, response.message().toString())
            }
        }
    }

    fun signIn() {
        CoroutineScope(Dispatchers.IO).launch {
            val userId = "choijaehoon.dev@gmail.com".toRequestBody("application/json".toMediaTypeOrNull())
            val password = "8412211111079122".toRequestBody("application/json".toMediaTypeOrNull())

            val response = UserApiImpl.getService().signIn(userId, password)

            if (response.isSuccessful) {
                _loginResult.postValue(response.body())
            } else {
                Log.e(TAG, response.code().toString())
                Log.e(TAG, response.message().toString())
            }
        }
    }

    fun createStudy() {
        CoroutineScope(Dispatchers.IO).launch {
            val userScheduleCreateDto = UserScheduleCreateDto(
                "와우",
                "2022-12-25"
            )

            val response = UserScheduleApiImpl.getService().createSchedule(
                App.pref.getAccessToken(),
                App.pref.getRefreshToken(),
                userScheduleCreateDto
            )

            if (response.isSuccessful) {
                Log.e(TAG, response.body().toString())
            } else {
                Log.e(TAG, response.code().toString())
                Log.e(TAG, response.message().toString())
            }
        }
    }

    fun getTemporaryPassword() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = UserApiImpl.getService().reissueTemporaryPassword("choijaehoon.dev@gmail.com")

            if(response.isSuccessful) {

            }
        }
    }
}