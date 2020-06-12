package com.extremeprogramming.financetracker.ui.login.Login.SignUp

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
import com.extremeprogramming.financetracker.R
import com.extremeprogramming.financetracker.backEndConnection.BackendEndPoints
import com.extremeprogramming.financetracker.backEndConnection.ServiceBuilder
import com.extremeprogramming.financetracker.backEndConnection.User
import com.extremeprogramming.financetracker.ui.login.Login.SignIn.SignInFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : Fragment() {

    private val signUpViewModel: SignUpViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val SignUp : Button = root.findViewById(R.id.SignUpBtn)
        SignUp.setOnClickListener { signUp() }

        val SingIn : TextView = root.findViewById(R.id.SignIn)
        SingIn.setOnClickListener { openSignInFragment() }

        return root
    }

    fun signUp(){

        val user = User(
            EmailTextInput.text.toString(),
            passwordInput.text.toString()
        )

        val request = ServiceBuilder.buildService(
            BackendEndPoints::class.java)
        val call = request.SingUp(user)

        enqueSignUp(call)
    }

    fun enqueSignUp(call : Call<ResponseBody>){
        call.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    Toast.makeText(activity!!.applicationContext,"Successfully signed up. You can login now!", Toast.LENGTH_LONG).show()
                    openSignInFragment()
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

    fun openSignInFragment(){
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        val newFragment = SignInFragment()
        fragmentTransaction.replace(R.id.FragmentContainer, newFragment)
        fragmentTransaction.commit()
    }
}