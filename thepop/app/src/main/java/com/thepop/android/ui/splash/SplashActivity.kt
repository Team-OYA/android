package com.thepop.android.ui.splash

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.thepop.android.MainActivity
import com.thepop.android.data.service.UserService
import com.thepop.android.databinding.ActivitySplashBinding
import com.thepop.android.domain.repository.CommunityRepository
import com.thepop.android.domain.repository.PopupRepository
import com.thepop.android.domain.repository.UserRepository
import com.thepop.android.util.ThepopSharedPrefernce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject lateinit var preference: ThepopSharedPrefernce
    @Inject lateinit var userRepository: UserRepository
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        val accessToken = preference.getAccessToken()
        if (!accessToken.isNullOrEmpty()){
            goToMainActivity()
        } else {
            setLoginButton()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setLoginButton() {
        binding.clLoginKakao.visibility = android.view.View.VISIBLE
        binding.clLoginKakao.setOnClickListener {
            // 카카오톡 설치 확인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                // 카카오톡 로그인
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    // 로그인 실패 부분
                    if (error != null) {
                        Log.e(TAG, "로그인 실패 $error")
                        // 사용자가 취소
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled ) {
                            return@loginWithKakaoTalk
                        }
                        // 다른 오류
                        else {
                            UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) // 카카오 이메일 로그인
                        }
                    }
                    // 로그인 성공 부분
                    else if (token != null) {
                        val e = Log.e(/* tag = */ TAG, /* msg = */ "로그인 성공 ${token.accessToken}")
                        lifecycleScope.launchWhenCreated {
                            try {
                                val response = userRepository.kakaoLogin(token.accessToken)
                                preference.setAccessToken(response.data.accessToken)
                                preference.setRefreshToken(response.data.refreshToken)
                            } catch (e: Exception) {
                                Log.e("로그인", "error: $e")
                            }
                        }
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) // 카카오 이메일 로그인
            }
        }
    }

    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "로그인 실패 $error")
        } else {
            Log.e(TAG, "로그인 성공 ${token?.accessToken ?: "액세스 토큰이 없음"}")
        }
    }

}
