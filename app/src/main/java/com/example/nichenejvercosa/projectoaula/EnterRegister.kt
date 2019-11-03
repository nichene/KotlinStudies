package com.example.nichenejvercosa.projectoaula

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class EnterRegister : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_register)


        val textName = findViewById<TextView>(R.id.txt_name_enterRegister)
        textName.text = intent.getStringExtra("Name")

        val textCPF = findViewById<TextView>(R.id.txt_cpf_enterRegister)
        textCPF.text = intent.getStringExtra("CPF")

    }
}
