package com.capou.application.ui.home

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.capou.application.R
import com.capou.application.databinding.FragmentHomeBinding
import com.capou.application.ui.details.DetailsFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.io.IOException
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var mMap: GoogleMap
    private var dataBaseGetInst = FirebaseDatabase.getInstance()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var databaseRef: DatabaseReference



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

            setMapLongClick(mMap)
            setPoiClick(mMap)

             databaseRef = Firebase.database.reference
              databaseRef.addValueEventListener(logListener)

        } })
    }

    private fun setMapLongClick(map: GoogleMap) {
        mMap.setOnMapClickListener {
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

            val databaseRef = dataBaseGetInst.getReference("points_de_vente")
            databaseRef.push().child("location").setValue(addressString)

        }
    }

    private fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener { point ->
            val poiMarker = map.addMarker(MarkerOptions().position(point.latLng).title(point.name))
            poiMarker?.showInfoWindow()
        }
    }

    // show data
    val logListener = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(context, "Could not read from database", Toast.LENGTH_LONG).show()
        }

        //     @SuppressLint("LongLogTag")
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            if (dataSnapshot.exists()) {

                val locationLogging = dataSnapshot.child("demo_address").getValue(LocationLogging::class.java)
                var driverLat = locationLogging?.Latitude
                var driverLong = locationLogging?.Longitude
                var nomAdd = locationLogging?.nomAd
                //Log.d("Latitude of driver", driverLat.toString())
                //    Log.d("Longitude read from database", driverLong.toString())

                if (driverLat !=null  && driverLong != null) {
                    val driverLoc = LatLng(driverLat, driverLong)

                    // Initializing Geocoder
                    val mGeocoder = Geocoder(context, Locale.getDefault())
                    var addressString= ""

                    // Reverse-Geocoding starts
                    try {
                        val addressList: List<Address> = mGeocoder.getFromLocation(driverLat, driverLong, 1)

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
                        Toast.makeText(context,"Unable connect to Geocoder",Toast.LENGTH_LONG).show()
                    }

                    //  val driverLoc = addressString
                    val markerOptions = MarkerOptions().position(driverLoc).title(addressString)
                    mMap.addMarker(markerOptions)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(driverLoc, 17f))
                    //Zoom level - 1: World, 5: Landmass/continent, 10: City, 15: Streets and 20: Buildings

                    Toast.makeText(context, "Locations accessed from the database", Toast.LENGTH_LONG).show()
                }
            }
        }
    }



}