package com.example.nichenejvercosa.projectoaula

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class CardAdapter (private val cards: MutableList<Card>): RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.card,p0,false))
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.title.text = cards[p1].title
        p0.subTitle.text = cards[p1].subTitle
        p0.status.text = cards[p1].status


        p0.deleteButton.setOnClickListener {
            cards.removeAt(p1)
            notifyItemRemoved(p1)
            notifyItemRangeChanged(p1, cards.size)
        }

        p0.checkButton.setOnClickListener {
            if(p0.status.text.equals("Não")){
                p0.status.text = "Sim"
            } else{
                p0.status.text = "Não"
            }

        }
    }

    fun addCard(card: Card){
        cards.add(card)
        notifyItemInserted(itemCount)
    }
}