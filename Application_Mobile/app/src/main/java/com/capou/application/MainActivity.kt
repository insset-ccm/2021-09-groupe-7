package com.capou.application

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capou.application.databinding.ActivityMainBinding
import com.capou.application.ui.aliments.fragments.HomeFragmentAliment
import com.capou.application.ui.aliments.repository.AlimentRepository
import com.capou.application.ui.authentification.SignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var authentification: FirebaseAuth
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authentification = Firebase.auth
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_product, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this
        val logoutMenu =  menuInflater.inflate(R.menu.logout_menu, menu)
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                // Action goes here
                Log.d(TAG,"Deconnexion");
                Toast.makeText(applicationContext,""+ authentification.signOut(),Toast.LENGTH_LONG).show()
                Log.d(TAG, authentification.currentUser.toString()+""+ authentification.currentUser?.email);
                authentification.signOut();
                Log.d(TAG, authentification.currentUser.toString()+""+ authentification.currentUser?.email);
                val intent  = Intent(applicationContext,SignIn::class.java);
                startActivity(intent);
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

