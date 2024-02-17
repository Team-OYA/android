package com.thepop.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thepop.android.databinding.ActivityMainBinding
import com.thepop.android.ui.community.CommunityFragment
import com.thepop.android.ui.home.HomeFragment
import com.thepop.android.ui.mypage.MyPageFragment
import com.thepop.android.ui.taste.TasteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

            if (currentFragment != null && item.itemId == currentFragment.tag?.toInt()) {
                return@setOnNavigationItemSelectedListener false
            }
            navigateToFragment(item.itemId)
            true
        }
        binding.bottomNavigation.selectedItemId = R.id.navi_home
    }

    private fun navigateToFragment(itemId: Int): Boolean {
        when (itemId) {
            R.id.navi_home -> navigateTo(HomeFragment())
            R.id.navi_community -> navigateTo(CommunityFragment())
            R.id.navi_taste -> goToTaste()
            R.id.navi_mypage -> navigateTo(MyPageFragment())
        }
        return true
    }

    private fun navigateTo(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }

    private fun goToTaste() {
        val intent = Intent(this, TasteActivity::class.java)
        startActivity(intent)
    }

}