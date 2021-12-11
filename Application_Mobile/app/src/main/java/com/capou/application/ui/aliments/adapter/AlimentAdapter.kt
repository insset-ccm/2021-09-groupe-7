package com.capou.application.ui.aliments.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capou.application.R
import com.capou.application.ui.aliments.modal.AlimentModal

class AlimentAdapter(
    private val alimentList: ArrayList<AlimentModal>,
    private val layoutId: Int

) : RecyclerView.Adapter<AlimentAdapter.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val alimentImage = view.findViewById<ImageView>(R.id.image_item)
        val alimentName : TextView? = view.findViewById(R.id.name_item)
        val saison : TextView? = view.findViewById(R.id.saison_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAliment = alimentList[position]

        //utiliser glide pour recuperer l'image à partir de son lien -> composant
        //Glide.with(context).load(Uri.parse(currentAliment.imageUrl)).into(holder.alimentImage)
        //mettre à jour le nom
        holder.alimentName?.text = currentAliment.name
        //  saison
        holder.saison?.text = currentAliment.saison
    }
    override fun getItemCount(): Int = alimentList.size
}