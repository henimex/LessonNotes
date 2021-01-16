package com.hendev.notes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NotlarAdapter(private val mContext: Context, private val notlarListe: List<Notlar>)
    :RecyclerView.Adapter<NotlarAdapter.designHolder>() {

    inner class designHolder(x:View):RecyclerView.ViewHolder(x){
        var not_card : CardView
        var txtDers : TextView
        var txtSinav1 : TextView
        var txtSinav2 : TextView

        init {
            not_card = x.findViewById(R.id.not_card)
            txtDers = x.findViewById(R.id.txtDersAdi)
            txtSinav1 = x.findViewById(R.id.txtEx1)
            txtSinav2 = x.findViewById(R.id.txtEx2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): designHolder {
        val _design = LayoutInflater.from(mContext).inflate(R.layout.card_tasarim,parent,false)
        return designHolder(_design)
    }

    override fun onBindViewHolder(holder: designHolder, position: Int) {
        val not =notlarListe[position]

        holder.txtDers.text=not.ders_adi
        holder.txtSinav1.text=not.not1.toString()
        holder.txtSinav2.text=not.not2.toString()

        holder.not_card.setOnClickListener {
            val intent = Intent(mContext,DetayActivity::class.java)
            intent.putExtra("nesne", not)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return notlarListe.size
    }
}