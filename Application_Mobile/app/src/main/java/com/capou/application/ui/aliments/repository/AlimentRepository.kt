package com.capou.application.ui.aliments.repository

import com.capou.application.ui.aliments.modal.AlimentModal
import com.capou.application.ui.aliments.repository.AlimentRepository.Singleton.alimentList
import com.capou.application.ui.aliments.repository.AlimentRepository.Singleton.databaseRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AlimentRepository {
    object Singleton {
        // Se connecter à la reference "aliment"
        val databaseRef = FirebaseDatabase.getInstance().getReference("aliment")

        // creér une liste qui va contenir nos aliments
        val alimentList = arrayListOf<AlimentModal>()
    }
    fun updateData(callback: ()-> Unit) {
        // absorber les données depuis la dataBaseRef -> liste d'aliments
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                alimentList.clear()
                //recolter la liste
                for (ds in snapshot.children){
                    // construire un objet aliment
                    val aliment = ds.getValue(AlimentModal::class.java)
                    if (aliment != null){
                        alimentList.add(aliment)
                    }
                }
                callback()
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}