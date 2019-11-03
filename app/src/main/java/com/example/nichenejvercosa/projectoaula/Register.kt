package com.example.nichenejvercosa.projectoaula

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val edtName = findViewById<EditText>(R.id.edt_name_register)
        val edtCPF = findViewById<EditText>(R.id.edt_cpf_register)

        val buttonEnterRegister = findViewById<Button>(R.id.btn_enter_register)
        val buttonCancelRegister = findViewById<Button>(R.id.btn_cancel_register)

        buttonEnterRegister.setOnClickListener {
            val intentEnterRegister = Intent(this, EnterRegister::class.java)

            intentEnterRegister.putExtra("Name", edtName.text.toString())
            intentEnterRegister.putExtra("CPF", edtCPF.text.toString())

            startActivity(intentEnterRegister)
        }

        buttonCancelRegister.setOnClickListener {
            finish()
        }


    }
}
