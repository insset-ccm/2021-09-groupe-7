package com.capou.application.comments

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capou.application.Data
import com.capou.application.MainActivity
import com.capou.application.R
import com.capou.application.comments.Repository.CommentRepository
import com.capou.application.comments.fragments.FullscreenFragment
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
    private lateinit var default :CommentRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()


        default =  CommentRepository(intent.getStringExtra("title").toString())

        default.default.observe(this,{
            Log.d("Details"," "+it)
            adapter.submitList(it)
        })
        adapter = ChuckNorrisAdapter{
                item,view, type -> onItemClick(item, view,type)
        }

        binding.recyclerComment.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerComment.adapter = adapter
        //Toast.makeText(applicationContext,"List comments",Toast.LENGTH_LONG).show()
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
                          var date = result.child("date").getValue(String::class.java)
                          Log.d("De",auteur.toString())
                        element.add(CommentModel(message.toString(),date.toString(),auteur.toString()))

                      }
                    }
                Log.d("Details"," "+element.size)
                //adapter.submitList(element)





                   /* val result = snap.getValue(CommentModel::class.java)
                    element.add(result)
                    Log.d("DE","--> "+element.size)*/

            }

            override fun onCancelled(error: DatabaseError) {
             Log.d("Error","${error.message}")
            }
        })
        Log.d("Details"," "+element.size)

        binding.write.setOnClickListener {
           // val intent = Intent(applicationContext, Data::class.java)
           // startActivity(intent)
           // displayDialog()
            val product = intent.getStringExtra("title").toString()
            default.writeComment(product,"Super","now","Me")

          // showDialog()
        }

    }

    fun showDialog() {
        val fragmentManager = supportFragmentManager
        val newFragment = FullscreenFragment()

            // The device is smaller, so show the fragment fullscreen
            val transaction = fragmentManager.beginTransaction()
            // For a little polish, specify a transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity
     //   ragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, fragment).commit()
            transaction
                .replace(android.R.id.content, newFragment)
                .addToBackStack(null)
                .commit()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun onItemClick(restaurant: CommentModel, view : View, type: String) {
        // view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        //Toast.makeText(this,restaurant.auteur+" "+restaurant.message,Toast.LENGTH_LONG).show()

    }

    public fun displayDialog(){
       var builder =  AlertDialog.Builder(this)
        builder.apply {
            setView(R.layout.alertdialog_comment)
            setPositiveButton("Ok",
                DialogInterface.OnClickListener { dialog, id ->
                    //User clicked OK button
                   // Toast.makeText(applicationContext,"${id}",Toast.LENGTH_LONG).show()
                })
            setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                    dialog.cancel()
                })
        }
        val alert = builder.create()
        alert.show()
    }

}