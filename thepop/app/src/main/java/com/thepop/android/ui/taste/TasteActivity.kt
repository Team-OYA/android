package com.thepop.android.ui.taste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.thepop.android.R
import com.thepop.android.databinding.ActivityTasteBinding

class TasteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTasteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        var fragment = TasteLandingFragment()
        binding.ivBack.setOnClickListener {
            finish()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_taste, fragment)
            .commit()

    }

}