package com.example.nichenejvercosa.projectoaula

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class BitCoinAdapter (private val bitCards: MutableList<BitCoinNews>) : RecyclerView.Adapter<BitCoinViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BitCoinViewHolder {
        return BitCoinViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.bit_card,p0,false))
    }

    override fun getItemCount(): Int {
        return bitCards.size
    }

    override fun onBindViewHolder(p0: BitCoinViewHolder, p1: Int) {
        p0.title.text = bitCards[p1].title
        p0.description.text = bitCards[p1].description
    }

    fun addCard(bitCard: BitCoinNews){
        bitCards.add(bitCard)
        notifyItemInserted(itemCount)
    }
}