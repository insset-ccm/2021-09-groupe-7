package com.capou.application

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capou.application.databinding.ActivityMainBinding
import com.capou.application.ui.authentification.viewModel.FirebaseAuthViewModel
import com.capou.application.ui.authentification.view.SignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: FirebaseAuthViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var authentification: FirebaseAuth
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(FirebaseAuthViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authentification = Firebase.auth
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_product, R.id.navigation_detail
            )
        )

        viewModel.getUserInfo().observe(this,{
            Log.d("Details: ",it.toString())
            val getType :String? ="utilisateur"
            if(it.toString().lowercase()==getType){
                navView.menu.removeItem(R.id.navigation_home_maraicher)
                navView.menu.removeItem(R.id.navigation_list_aliment_maraicher)
                //navView.menu.removeItem(R.id.navigation_maraicher_profile)
            }
            else{
               //navView.menu.removeItem(R.id.navigation_home)
               // navView.menu.removeItem(R.id.navigation_product)
              //  navView.menu.removeItem(R.id.navigation_dashboard)

            }
        })

        //navView.menu.removeItem(R.id.navigation_product)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout_menu, menu)
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                // Action goes here
                authentification.signOut()
                val intent  = Intent(applicationContext, SignIn::class.java);
                startActivity(intent)
                var shared = getSharedPreferences("test", MODE_PRIVATE)
               shared.edit().remove("test").commit()

               val value = shared.getString("test","element-default")
                Log.d(TAG, "onOptionsItemSelected: "+ value)
                true
            }
            R.id.about -> {
                var alert = AlertDialog.Builder(this)
                alert.setTitle(R.string.about)
                alert.setMessage("Cette application a pour but d'inciter les utilisateurs ?? consommer des produits locaux et de saison ")
                alert.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                alert.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPause() {
        super.onPause()
        authentification.signOut()
    }


    override fun onDestroy() {
        super.onDestroy()
        authentification.signOut()
    }
}

