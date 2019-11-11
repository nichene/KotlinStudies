package com.example.nichenejvercosa.projectoaula

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ActivityList : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter
    private lateinit var mDialogView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val sharedPref = getSharedPreferences("MyPrefs", 0)
        val userName = sharedPref.getString("USERNAME", "")

        var userMessage = "Olá ${userName}! =)"

        var txtMessage = findViewById<TextView>(R.id.txt_activityList)
        txtMessage.text = userMessage

        recyclerView = findViewById<RecyclerView>(R.id.reclycle_view_activityList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CardAdapter(mutableListOf<Card>())

        recyclerView.adapter = adapter

        val fabButton = findViewById<FloatingActionButton>(R.id.floating_button_activityList)
        fabButton.setOnClickListener {

            val view : View = this.layoutInflater.inflate(R.layout.add_card_dialog, null)

            var edtTitle = view.findViewById<EditText>(R.id.edt_title_dialog)
            var edtSubtitle = view.findViewById<EditText>(R.id.edt_subtitle_dialog)

            val alert = android.app.AlertDialog.Builder(this)
            alert.setView(view)

                    .setPositiveButton(getString(R.string.ok),
                            DialogInterface.OnClickListener { dialog, id ->
                                val card = Card(edtTitle.text.toString(), edtSubtitle.text.toString(), getString(R.string.Not))
                                adapter.addCard(card)
                            })
                    .setNegativeButton(getString(R.string.Not),
                            DialogInterface.OnClickListener { dialog, id ->

                            })
           alert.show()



        }
    }


}
