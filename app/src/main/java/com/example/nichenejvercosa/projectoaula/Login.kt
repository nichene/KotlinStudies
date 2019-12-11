package com.example.nichenejvercosa.projectoaula

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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


        val db = MyDatabase(baseContext);


        buttonEnterLogin.setOnClickListener {

            var user = db.getUser(edtLogin.text.toString());

            if (user!=null && user.name.equals(edtLogin.text.toString()) && user.password.equals(edtPassword.text.toString())) {

                val intentEnterActivityList = Intent(this, ActivityList::class.java)
                intentEnterActivityList.putExtra("UserName", user.name)
                startActivity(intentEnterActivityList)

            } else if (user!=null && user.name.equals(edtLogin.text.toString()) && user.password != edtPassword.text.toString() ){
                Toast.makeText(this, "Senha Errada", Toast.LENGTH_SHORT).show()

            } else if(user?.name.equals("")){
                Toast.makeText(this, R.string.notRegistered, Toast.LENGTH_SHORT).show()
            }


        }

        buttonCancelLogin.setOnClickListener {
            finish()
        }

    }
}
