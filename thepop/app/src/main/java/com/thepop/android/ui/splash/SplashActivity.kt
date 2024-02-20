package com.thepop.android.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thepop.android.MainActivity
import com.thepop.android.databinding.ActivitySplashBinding
import com.thepop.android.util.ThepopSharedPrefernce
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject lateinit var preference: ThepopSharedPrefernce

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        val accessToken = preference.getAccessToken()
        if (accessToken != null) {
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
    }
}
