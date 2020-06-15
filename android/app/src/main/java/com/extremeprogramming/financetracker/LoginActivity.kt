package com.extremeprogramming.financetracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.extremeprogramming.financetracker.backEndConnection.ServiceBuilder
import com.extremeprogramming.financetracker.db.Converters
import com.extremeprogramming.financetracker.ui.login.Login.SignIn.SignInFragment
import org.threeten.bp.LocalDateTime

class LoginActivity : AppCompatActivity() {
    private lateinit var fragment : Fragment
    private var loaded: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragment = SignInFragment()
        val loggedDate = getPreferences(Context.MODE_PRIVATE)?.getString(getString(R.string.SharedPrefDate),Converters.fromDateTime(LocalDateTime.of(2000,1,1,1,1)))
        val loggedDateConverted = Converters.toDateTime(loggedDate)
        if (loggedDate != "" && !LocalDateTime.now().isAfter(loggedDateConverted?.plusDays(10))){// && true == false){
            loaded = false
            setKey()
            openApplication()
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

    fun setKey(){
        ServiceBuilder.key = getPreferences(Context.MODE_PRIVATE)?.getString(getString(R.string.SharedPrefToken),"")
    }

    fun openApplication(){
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