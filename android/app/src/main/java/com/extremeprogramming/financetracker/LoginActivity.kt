package com.extremeprogramming.financetracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.extremeprogramming.financetracker.ui.login.Login.SignIn.SignInFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var fragment : Fragment
    private var loaded: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragment = SignInFragment()
        if (getPreferences(Context.MODE_PRIVATE)?.getString(getString(R.string.SharedPrefDate),"") != ""){// && true == false){
            loaded = false
            OpenApplication()
        }
        else{
            setContentView(R.layout.login_activity)
            val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()

            if (savedInstanceState != null){
                fragment = supportFragmentManager.getFragment(savedInstanceState, fragmentKey)!!
            }
            fragmentTransaction.replace(R.id.FragmentContainer,fragment)
            fragmentTransaction.commit()
        }
    }

    fun OpenApplication(){
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
        this.startActivity(intent)
    }

    companion object {
        val fragmentKey = "myFragment"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (loaded) supportFragmentManager.putFragment(outState, fragmentKey,fragment)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}