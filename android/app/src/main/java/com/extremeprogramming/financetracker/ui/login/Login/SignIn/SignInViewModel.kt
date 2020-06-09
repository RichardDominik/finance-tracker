package com.extremeprogramming.financetracker.ui.login.Login.SignIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class SignInViewModel : ViewModel() {

    val messages = mutableListOf(
        "Hi there! Nice to see you again.",
        "Welcome ! We were expecting you ( ͡° ͜ʖ ͡°)",
        "It's a bird! It's a plane! Nevermind, it's just you.",
        "It's dangerous to go alone, take us with you!",

        "We have waited so long to have you among us. At last, the time has come.",
        "Your presence is nothing less than a blessing to us!",
        "The times we spend with you is always so full of joy and happiness.",
        "We know the value of each penny you spend on buying products.",
        "We are very happy to see you. Welcome to our happy family!"
    )
    private val _RndText = MutableLiveData<String>().apply {
        value = messages.get(Random.nextInt(messages.size))
    }
    val rndText: LiveData<String> = _RndText
}