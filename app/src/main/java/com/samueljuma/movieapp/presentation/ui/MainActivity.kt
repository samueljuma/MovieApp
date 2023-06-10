package com.samueljuma.movieapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.samueljuma.movieapp.R
import com.samueljuma.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout

            val navController = findNavController(R.id.nav_host_fragment_container)
            NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

            appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
            NavigationUI.setupWithNavController(binding.navView, navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment_container)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}