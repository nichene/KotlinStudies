package com.example.nichenejvercosa.projectoaula

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class EnterLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_login)


        val textLogin = findViewById<TextView>(R.id.txt_login_enterLogin)
        textLogin.text = intent.getStringExtra("Login")

        val textPassword = findViewById<TextView>(R.id.txt_password_enterLogin)
        textPassword.text = intent.getStringExtra("Password")
    }
}
