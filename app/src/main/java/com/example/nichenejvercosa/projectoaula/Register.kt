package com.example.nichenejvercosa.projectoaula

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.nichenejvercosa.projectoaula.R.string.email
import android.text.Editable
import android.text.TextWatcher



class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val edtName = findViewById<EditText>(R.id.edt_name_register)
        val edtCPF = findViewById<EditText>(R.id.edt_cpf_register)
        val edtPass = findViewById<EditText>(R.id.edt_password_register)

        val buttonEnterRegister = findViewById<Button>(R.id.btn_enter_register)
        val buttonCancelRegister = findViewById<Button>(R.id.btn_cancel_register)

        checkEmail(this)

        val db = MyDatabase(baseContext);


        buttonEnterRegister.setOnClickListener {

            if(edtName.text.toString() != "" && edtPass.text.toString() != ""){

                db.insertUser(edtName.text.toString(), edtPass.text.toString())

                val intentEnterActivityList = Intent(this, ActivityList::class.java)
                intentEnterActivityList.putExtra("UserName", edtName.text.toString())
                startActivity(intentEnterActivityList)

            }else{
                Toast.makeText(this, R.string.notFilledOut, Toast.LENGTH_SHORT).show()
            }



        }

        buttonCancelRegister.setOnClickListener {
            finish()
        }


    }

    private fun checkEmail(context: Context) {

        var email = findViewById<EditText>(R.id.edt_email_register)

        email.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                Is_Valid_Email(email)
            }

            fun Is_Valid_Email(edt: EditText) {
                var validEmail : String

                if (edt.text.toString() == null) {
                    Toast.makeText(context, "Invalid Email Address", Toast.LENGTH_SHORT).show()

                } else if (isEmailValid(edt.text.toString()) == false) {
                    Toast.makeText(context, "Invalid Email Address", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Valid Email Address", Toast.LENGTH_SHORT).show()
                    validEmail = edt.text.toString()
                }
            }

            internal fun isEmailValid(email: CharSequence): Boolean {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches()
            } // end of TextWatcher (email)
        })

    }
}
