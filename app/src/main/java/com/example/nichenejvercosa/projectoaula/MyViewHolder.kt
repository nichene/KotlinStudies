package com.example.nichenejvercosa.projectoaula

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title: TextView = itemView.findViewById(R.id.txt_title_card)
    var subTitle: TextView = itemView.findViewById(R.id.txt_subtitle_card)
    var status: TextView = itemView.findViewById(R.id.txt_status_card)
    var deleteButton: ImageButton = itemView.findViewById(R.id.btn_item_delete_card)
    var checkButton: ImageButton = itemView.findViewById(R.id.btn_check_card)

}