package com.example.nichenejvercosa.projectoaula

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edtLogin = findViewById<EditText>(R.id.edt_login_login)
        val edtPassword = findViewById<EditText>(R.id.edt_password_login)

        val buttonEnterLogin = findViewById<Button>(R.id.btn_enter_login)
        val buttonCancelLogin = findViewById<Button>(R.id.btn_cancel_login)

        val sharedPref = getSharedPreferences("MyPrefs", 0)
        val userName = sharedPref.getString("USERNAME", "")
        val userPass = sharedPref.getString("USERPASS", "")

        buttonEnterLogin.setOnClickListener {

           if( userName.equals(edtLogin.text.toString())  && userPass.equals(edtPassword.text.toString())
           && userName != "" && userPass != ""){

               val intentEnterActivityList = Intent(this, ActivityList::class.java)

               // Comments made for studying
               // if you want to pass any data to the next activity
               // do it with putExtra
               // as shown below
               // intentEnterRegister.putExtra("Login", edtLogin.text.toString())
               // intentEnterRegister.putExtra("Password", edtPassword.text.toString())

               startActivity(intentEnterActivityList)

           }else{
               Toast.makeText(this, R.string.notRegistered, Toast.LENGTH_SHORT).show()
           }

        }

        buttonCancelLogin.setOnClickListener {
            finish()
        }

    }
}
