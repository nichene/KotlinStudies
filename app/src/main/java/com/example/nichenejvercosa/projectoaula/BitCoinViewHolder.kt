package com.example.nichenejvercosa.projectoaula

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class BitCoinViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title: TextView = itemView.findViewById(R.id.txt_title_bit_card)
    var description: TextView = itemView.findViewById(R.id.txt_description_bit_card)
}