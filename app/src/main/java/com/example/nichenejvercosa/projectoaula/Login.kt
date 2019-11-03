package com.example.nichenejvercosa.projectoaula

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val edtLogin = findViewById<EditText>(R.id.edt_login_login)
        val edtPassword = findViewById<EditText>(R.id.edt_password_login)

        val buttonEnterLogin = findViewById<Button>(R.id.btn_enter_login)
        val buttonCancelLogin = findViewById<Button>(R.id.btn_cancel_login)

        buttonEnterLogin.setOnClickListener {
            val intentEnterRegister = Intent(this, EnterLogin::class.java)

            intentEnterRegister.putExtra("Login", edtLogin.text.toString())
            intentEnterRegister.putExtra("Password", edtPassword.text.toString())

            startActivity(intentEnterRegister)
        }

        buttonCancelLogin.setOnClickListener {
            finish()
        }

    }
}
