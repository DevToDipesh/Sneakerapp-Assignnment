package com.cricbuzz.assignment.dipesh.sneakerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.cricbuzz.assignment.dipesh.sneakerapp.cart.ui.CartFragment
import com.cricbuzz.assignment.dipesh.sneakerapp.databinding.ActivityMainBinding
import com.cricbuzz.assignment.dipesh.sneakerapp.detail.ui.SneakerDetailFragment
import com.cricbuzz.assignment.dipesh.sneakerapp.home.ui.HomeFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCart.setOnClickListener {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            val currentFragment = navHostFragment.childFragmentManager.fragments.get(0)
            if (currentFragment != null && currentFragment is CartFragment) {
                return@setOnClickListener
            }
            if (currentFragment != null && currentFragment is HomeFragment) {

                navController.navigate(R.id.action_homeFragment_to_cartFragment)
            } else if (currentFragment != null && currentFragment is SneakerDetailFragment) {
                navController.navigate(R.id.action_sneakerDetailFragment_to_cartFragment)

            }
        }
        binding.btnHome.setOnClickListener {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            val currentFragment = navHostFragment.childFragmentManager.fragments.get(0)
            if (currentFragment != null && currentFragment is CartFragment) {
                navController.navigate(R.id.action_cartFragment_to_homeFragment)

            }

        }
    }
}