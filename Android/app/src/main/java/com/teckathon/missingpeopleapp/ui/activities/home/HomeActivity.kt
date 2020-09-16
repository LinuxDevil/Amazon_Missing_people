package com.teckathon.missingpeopleapp.ui.activities.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.teckathon.missingpeopleapp.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupUI()
    }

    /**
     *
     * @return Boolean
     */
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.fragment), drawerLayout)
    }

    /**
     *
     */
    private fun setupUI() {
        setSupportActionBar(toolbar)
        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(navigationView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }
}