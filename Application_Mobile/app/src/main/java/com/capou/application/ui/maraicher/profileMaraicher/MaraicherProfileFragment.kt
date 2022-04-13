package com.capou.application.ui.maraicher.profileMaraicher

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.capou.application.databinding.FragmentMaraicherProfileBinding
import com.capou.application.model.AlimentModel
import com.capou.application.model.UserModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MaraicherProfileFragment : Fragment() {

    private var data = Firebase.database.reference
    private lateinit var maraicherProfileViewModel: MaraicherProfileViewModel
    private lateinit var binding: FragmentMaraicherProfileBinding
    private val auth = Firebase.auth
    // This property is only valid between onCreateView and
    // onDestroyView.
   // private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        maraicherProfileViewModel =
            ViewModelProvider(this).get(MaraicherProfileViewModel::class.java)

        binding = FragmentMaraicherProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val textView: TextView = binding.textMaraicherProfile
        maraicherProfileViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

       val id = auth.currentUser?.uid.toString()
       val query = data.child("utilisateurs")

      query.child(id).addValueEventListener(object : ValueEventListener {
          override fun onDataChange(snapshot: DataSnapshot) {
              Log.d("Profil","${snapshot}")
              binding.itemFirstname.text = snapshot.child("nom").getValue(String::class.java)
              binding.itemLastname.text = snapshot.child("prenom").getValue(String::class.java)
              binding.textMaraicherProfile.text = snapshot.child("type").getValue(String::class.java)
          }

          override fun onCancelled(error: DatabaseError) {
              Log.d("Tag",error.message)
          }})

        return root
    }
/*

 private fun onItemClick (aliment: AlimentModel, view: View){
        Log.d("Details: ",aliment.nom)
        val fragment:Fragment = DetailsFragment(aliment.nom,aliment.images);
        val fragmentManager : FragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, fragment).addToBackStack(DetailsFragment::class.java.name).commit()
    }
 */

}