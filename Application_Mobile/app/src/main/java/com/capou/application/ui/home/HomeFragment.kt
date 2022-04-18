package com.capou.application.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var mMap: GoogleMap
    private var data = Firebase.database.reference
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    // private var _binding: FragmentHomeBinding? = null
    //private val binding get() = _binding!!

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

    override fun onStart() {
        super.onStart()
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync( object : OnMapReadyCallback {
            override fun onMapReady(googleMap: GoogleMap) {
           mMap = googleMap

            val currentCity = LatLng(49.83689727421656, 3.3002214823430656)
            val zoom = 55f
            mMap.addMarker(MarkerOptions().position(currentCity).title("Insset saint quentin"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentCity))

            setMapLongClick(mMap)
            setPoiClick(mMap)

        } })
    }
    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapClickListener {
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %15.5f, Lng: %25.5f",
                it.latitude,
                it.longitude
            )
            map.addMarker(MarkerOptions().position(it).
            title("pick up ").snippet(snippet))

        }
    }
    private fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener { point ->
            val poiMarker = map.addMarker(MarkerOptions().position(point.latLng).title(point.name))
            poiMarker?.showInfoWindow()
        }
    }

}