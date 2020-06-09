package com.extremeprogramming.financetracker.backEndConnection

class User(val email: String, val password: String){
    override fun toString(): String {
        return "Category [email: ${this.email}, password: ${this.password}]"
    }
}