package com.capou.application.ui.home

import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capou.application.R
import com.capou.application.databinding.FragmentHomeBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.io.IOException
import java.util.*
import com.google.android.gms.maps.model.LatLng


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var mMap: GoogleMap

    private var dataBaseGetInst = FirebaseDatabase.getInstance()
    private lateinit var databaseRef: DatabaseReference
    private val auth = Firebase.auth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }

    // DATA CLASS
    data class LocationLogging(
        var nomAd: String = "",
        var Latitude: Double? = 0.0,
        var Longitude: Double? = 0.0
    )

    override fun onStart() {
        super.onStart()
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync( object : OnMapReadyCallback {
           override fun onMapReady(googleMap: GoogleMap) {
           mMap = googleMap
            val currentCity = LatLng(49.83689727421656, 3.3002214823430656)
            mMap.addMarker(MarkerOptions().position(currentCity).title("Insset Saint Quentin"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentCity, 17F))

           setPoiClick(mMap)

           databaseRef = FirebaseDatabase.getInstance().getReference()
           val idCurrentUser = auth.currentUser?.uid.toString()
           val queryGetUser = databaseRef.child("utilisateurs").child(idCurrentUser)

               queryGetUser.addValueEventListener(object : ValueEventListener {
                   override fun onDataChange(snapshot: DataSnapshot) {
                       val typeUser = snapshot.child("type").getValue(String::class.java)

                       if( typeUser == "Utilisateur") {
                          val pickupPointCommun = databaseRef.child("demo_point_vente")
                           pickupPointCommun.addValueEventListener(object : ValueEventListener {
                               override fun onDataChange(snapshot: DataSnapshot) {

                                   for(result_point in snapshot.children){
                                       pickupPointCommun.child(result_point.key.toString()).addValueEventListener(showMarker)
                                   }
                               }
                               override fun onCancelled(error: DatabaseError) {
                                   Log.d("Tag",error.message)
                               }
                           })
                       }else if (typeUser == "Maraîcher"){

                           val pickupPointMaraicher = databaseRef.child("demo_point_vente").child(idCurrentUser)
                           pickupPointMaraicher.addValueEventListener(showMarker)
                           setMapLongClick(mMap)

                               // Draggin Marker
                               mMap.setOnMarkerDragListener(object : OnMarkerDragListener {
                                   override fun onMarkerDragStart(marker: Marker) {
                                       pickupPointMaraicher.removeValue()

                                   }
                                   override fun onMarkerDrag(marker: Marker) {
                                     // pickupPointMaraicher.removeValue()

                                   }
                                   override fun onMarkerDragEnd(marker: Marker) {
                                        marker.remove()

                                       val newLatLng = LatLng(marker.position.latitude, marker.position.longitude)
                                       // Initializing Geocoder
                                       val mGeocoder = Geocoder(context, Locale.getDefault())
                                       var addressString= ""

                                       // Reverse-Geocoding starts
                                       try {
                                           val addressListDrag: List<Address> = mGeocoder.getFromLocation(marker.position!!.latitude, marker.position!!.longitude, 1)

                                           if (addressListDrag != null && addressListDrag.isNotEmpty()) {

                                               val addressDrag = addressListDrag[0]
                                               val sb = StringBuilder()
                                               for (i in 0 until addressDrag.maxAddressLineIndex) {
                                                   sb.append(addressDrag.getAddressLine(i)).append("\n")
                                               }

                                               if (addressDrag.premises != null)
                                                   sb.append(addressDrag.premises).append(", ")
                                               sb.append(addressDrag.getAddressLine(0))
                                               addressString = sb.toString()
                                           }
                                       } catch (e: IOException) {
                                           Toast.makeText(context,"Connexion impossible à Geocoder", Toast.LENGTH_LONG).show()
                                       }

                                       mMap.addMarker(MarkerOptions().position(newLatLng).title(addressString).snippet("$typeUser"))
                                       val idCurrentMara = auth.currentUser?.uid.toString()

                                       val latlongAdst = LocationLogging(addressString,marker.position.latitude, marker.position.longitude)
                                       val databaseRef = dataBaseGetInst.getReference("demo_point_vente").child(idCurrentMara)
                                       val dataChild = databaseRef.push()
                                       dataChild.setValue(latlongAdst)
                                   }
                               })
                       }

                   }

                   override fun onCancelled(error: DatabaseError) {
                       Log.d("Tag",error.message)
                   }})
        } })
    }

    //Add marker
    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapClickListener {
            val lat = it.latitude
            val lng = it.longitude

            // Initializing Geocoder
            val mGeocoder = Geocoder(context, Locale.getDefault())
            var addressString= ""

            // Reverse-Geocoding starts
            try {
                val addressList: List<Address> = mGeocoder.getFromLocation(lat, lng, 1)

                if (addressList != null && addressList.isNotEmpty()) {

                    val address = addressList[0]
                    val sb = StringBuilder()
                    for (i in 0 until address.maxAddressLineIndex) {
                        sb.append(address.getAddressLine(i)).append("\n")
                    }

                    if (address.premises != null)
                        sb.append(address.premises).append(", ")
                    sb.append(address.getAddressLine(0))
                    addressString = sb.toString()
                }
            } catch (e: IOException) {
                Toast.makeText(context,"Impossible de se connecter à Geocoder", Toast.LENGTH_LONG).show()
            }

            mMap.addMarker(MarkerOptions().position(it).title(addressString))
            val idCurrentMara = auth.currentUser?.uid.toString()

            val latlongAdst = LocationLogging(addressString,it.latitude,it.longitude)
            val databaseRef = dataBaseGetInst.getReference("demo_point_vente").child(idCurrentMara)
            //databaseRef.push().child("location").setValue(addressString)
            val dataChild = databaseRef.push()
            //dataChild.setValue(addressTV)
            dataChild.setValue(latlongAdst)

        }
    }

    private fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener { point ->
            val poiMarker = map.addMarker(MarkerOptions().position(point.latLng).title(point.name))
            poiMarker?.showInfoWindow()
        }
    }

    // show data
    private fun displyMarker(dataSnapshot: DataSnapshot){
        for ( result in dataSnapshot.children) {

            val locationLoggingNom = result.child("nomAd").getValue(String::class.java)
            val locationLoggingLa = result.child("latitude").getValue(Double::class.java)
            val locationLoggingLon = result.child("longitude").getValue(Double::class.java)

            val driverLat = locationLoggingLa
            val driverLong = locationLoggingLon

            if (driverLat !=null  && driverLong != null) {
                val driverLoc = LatLng(driverLat, driverLong)

                val markerOptions =
                    MarkerOptions().position(driverLoc).title(locationLoggingNom).zIndex(0.0f).draggable(true)
                mMap.addMarker(markerOptions)
                mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f))
                Toast.makeText(
                    context,
                    "Locations accessed from the database",
                    Toast.LENGTH_LONG
                ).show()

            }
        }
    }

    val showMarker = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(context, "Impossible de lire les données de la base", Toast.LENGTH_LONG).show()
        }
        //     @SuppressLint("LongLogTag")
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            if (dataSnapshot.exists()) {
                displyMarker(dataSnapshot)
            }
        }
    }

}