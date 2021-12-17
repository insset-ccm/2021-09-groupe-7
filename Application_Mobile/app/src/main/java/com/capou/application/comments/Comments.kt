package com.capou.application.comments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capou.application.comments.Repository.CommentRepository
import com.capou.application.databinding.ActivityCommentsBinding
import com.capou.application.model.CommentModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Comments : AppCompatActivity() {

    private lateinit var binding: ActivityCommentsBinding
    private var data = Firebase.database.reference
    private var element = arrayListOf<CommentModel?>()
    private lateinit var adapter: ChuckNorrisAdapter
    private val default :CommentRepository by lazy { CommentRepository() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        default.default.observe(this,{
            Log.d("Details"," "+it)
            adapter.submitList(it)
        })
        adapter = ChuckNorrisAdapter{
                item,view, type -> onItemClick(item, view,type)
        }

        binding.recyclerComment.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerComment.adapter = adapter
        Toast.makeText(applicationContext,"List comments",Toast.LENGTH_LONG).show()
        var query = data.child("commentaire")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                    for(snap in snapshot.children){
                      for (result in snap.children){
                          //val display = result.getValue(CommentModel::class.java)
                          //Log.d("DE","--> "+display)
                          Log.d("De"," "+result)
                          var auteur = result.child("auteur").getValue(String::class.java)
                          var message = result.child("message").getValue(String::class.java)
                          Log.d("De",auteur.toString())
                        element.add(CommentModel("0",message.toString(),"23-11-2020",auteur.toString()))

                      }
                    }
                Log.d("Details"," "+element.size)
                //adapter.submitList(element)





                   /* val result = snap.getValue(CommentModel::class.java)
                    element.add(result)
                    Log.d("DE","--> "+element.size)*/

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        Log.d("Details"," "+element.size)


    }

    private fun onItemClick(restaurant: CommentModel, view : View, type: String) {
        // view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this,restaurant.auteurs+" "+restaurant.message,Toast.LENGTH_LONG).show()

    }

}