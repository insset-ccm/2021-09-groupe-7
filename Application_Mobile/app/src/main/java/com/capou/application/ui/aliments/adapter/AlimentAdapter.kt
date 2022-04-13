package com.capou.application.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capou.application.databinding.ItemVerticalAlimentBinding
import com.capou.application.helper.FonctionHelper
import com.capou.application.model.AlimentModel

val diffUtilsAliment = object : DiffUtil.ItemCallback<AlimentModel>() {
    override fun areItemsTheSame(oldItem: AlimentModel, newItem: AlimentModel): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: AlimentModel, newItem: AlimentModel): Boolean {
        return oldItem == newItem
    }


}

class AlimentViewHolder(
    val binding: ItemVerticalAlimentBinding,
    onItemClick: (objectDataSample: AlimentModel, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var ui: AlimentModel

    init {
        binding.root.setOnClickListener {
            onItemClick(ui, itemView)
        }

    }


    fun bind(chuckNorrisUi: AlimentModel) {
        ui = chuckNorrisUi


         Glide.with(itemView.context)
             .load(chuckNorrisUi.images)
             .into(binding.imageItem)
        val text = FonctionHelper().helpterText(chuckNorrisUi.nom)
        var saison = "Saison: "
        for (list in chuckNorrisUi.saison){
            saison += list?.let { FonctionHelper().helpterText(it) }.toString()+" "
        }
        binding.nameItem.text = text
        binding.saisonItem.text = saison.trim()



    }
}

class AlimentAdapter(private val onItemClick: (quoteUi: AlimentModel, view: View) -> Unit) : ListAdapter<AlimentModel, AlimentViewHolder>(
    diffUtilsAliment) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlimentViewHolder {
        return AlimentViewHolder(
            ItemVerticalAlimentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),onItemClick
        )
    }


    override fun onBindViewHolder(holder: AlimentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}



