package com.example.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationButton : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = Navigation.findNavController(this, R.id.newsNavHostFragment)

        navigationButton = binding.bottomNavigationView

        NavigationUI.setupActionBarWithNavController(this, navController )
        NavigationUI.setupWithNavController(navigationButton, navController)

    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = Navigation.findNavController(this, R.id.newsNavHostFragment)

        return navController.navigateUp() or super.onSupportNavigateUp()

    }

//    private fun setUpBottomNavigationBar() {
//
//        val bottomNavigationView: BottomNavigationView = binding.bottomNavigationView
//        val navController = findNavController(R.id.newsNavHostFragment)
//
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.breakingNewsFragment,  R.id.searchNewsFragment, R.id.savedNewsFragment, R.id.articleNewsFragment
//            )
//        )
//
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        bottomNavigationView.setupWithNavController(navController)
//
//    }
}