package com.example.nichenejvercosa.projectoaula

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val edtName = findViewById<EditText>(R.id.edt_name_register)
        val edtCPF = findViewById<EditText>(R.id.edt_cpf_register)
        val edtPass = findViewById<EditText>(R.id.edt_password_register)

        val buttonEnterRegister = findViewById<Button>(R.id.btn_enter_register)
        val buttonCancelRegister = findViewById<Button>(R.id.btn_cancel_register)

        val sharedPref = getSharedPreferences("MyPrefs", 0)
        val sharedPrefEditor = sharedPref.edit()


        buttonEnterRegister.setOnClickListener {

            if(edtName.text.toString() != "" && edtPass.text.toString() != ""){
                sharedPrefEditor.putString("USERNAME", edtName.text.toString())
                sharedPrefEditor.putString("USERPASS", edtPass.text.toString())
                sharedPrefEditor.apply()

                val intentEnterActivityList = Intent(this, ActivityList::class.java)

                startActivity(intentEnterActivityList)
            }else{
                Toast.makeText(this, R.string.notFilledOut, Toast.LENGTH_SHORT).show()
            }



        }

        buttonCancelRegister.setOnClickListener {
            finish()
        }


    }
}
