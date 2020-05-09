package com.extremeprogramming.financetracker.ui.login.Login.SignIn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.extremeprogramming.financetracker.MainActivity
import com.extremeprogramming.financetracker.R
import com.extremeprogramming.financetracker.backEndConnection.BackendEndPoints
import com.extremeprogramming.financetracker.backEndConnection.ServiceBuilder
import com.extremeprogramming.financetracker.ui.login.Login.SignUp.SignUpFragment
import com.extremeprogramming.financetracker.backEndConnection.User
import kotlinx.android.synthetic.main.fragment_sign_in.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInFragment : Fragment() {

    private val signInViewModel: SignInViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sign_in, container, false)
        val randomTextObj: TextView = root.findViewById(R.id.welcomeMsg)
        val txt : LiveData<String> = signInViewModel.rndText
        txt.observe(this, Observer {
            randomTextObj.text = it.toString()
        })

        val SingUp : TextView = root.findViewById(R.id.SignUp)
        SingUp.setOnClickListener { OpenSignUpFragment() }

        val SignIn : Button = root.findViewById(R.id.SignInButton)
        SignIn.setOnClickListener { Login() }
        return root
    }

    companion object {
        val Replace = "SouldReplace"
    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState.run {
            putBoolean(Replace, true)
        }
        super.onSaveInstanceState(outState)
    }

    fun Login(){

        val user = User(
            EmailInputText.text.toString(),
            PasswordInput.text.toString()
        )

        val request = ServiceBuilder.buildService(BackendEndPoints::class.java)
        val call = request.PostLogin(user)

        enqueLogin(call)
    }

    fun enqueLogin(call : Call<ResponseBody>){
        call.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    Toast.makeText(activity!!.applicationContext,"Successfully logged in!",Toast.LENGTH_SHORT).show()
                    OpenApplication()
                }
                else{
                    Toast.makeText(activity!!.applicationContext,"Incorrect email or password!",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("LOGIN1",t.message!!)
                Toast.makeText(activity,"Cannot connect to server!",Toast.LENGTH_SHORT).show()
            }
        }
        )
    }

    fun OpenApplication(){
        val intent = Intent(activity, MainActivity::class.java)
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
        activity!!.startActivity(intent)
    }

    fun OpenSignUpFragment(){
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        val newFragment = SignUpFragment()
        fragmentTransaction.replace(R.id.FragmentContainer, newFragment)
        fragmentTransaction.commit()
    }
}