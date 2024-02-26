package com.thepop.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.thepop.android.databinding.ActivityMainBinding
import com.thepop.android.ui.taste.TasteActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        navView = binding.bottomNavigation
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navi_home -> {
                    navController.navigate(R.id.fragment_home)
                    true
                }
                R.id.navi_community -> {
                    navController.navigate(R.id.fragment_community)
                    true
                }
                R.id.navi_taste -> {
                    goToTaste()
                    true
                }
                R.id.navi_mypage -> {
                    navController.navigate(R.id.fragment_mypage)
                    true
                }
                else -> false
            }
        }

        // Handle bottom navigation item reselection
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_home -> {
                    navView.menu.findItem(R.id.navi_home).isChecked = true
                }
                R.id.fragment_community -> {
                    navView.menu.findItem(R.id.navi_community).isChecked = true
                }
                R.id.fragment_mypage -> {
                    navView.menu.findItem(R.id.navi_mypage).isChecked = true
                }
            }
        }
    }



    private fun goToTaste() {
        intent = Intent(this, TasteActivity::class.java)
        startActivity(intent)
    }


}
