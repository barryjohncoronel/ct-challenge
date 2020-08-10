package com.example.cartrack.ui.map

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cartrack.R
import com.example.cartrack.data.model.UserFromApi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val EXTRA_USER = "EXTRA_USER"
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onStart() {
        super.onStart()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val user = intent.getParcelableExtra<UserFromApi>(EXTRA_USER)

        user?.let {
            val userLatLng = LatLng(
                user.address.geo.lat.toDouble(),
                user.address.geo.lng.toDouble()
            )

            val markerOptions = MarkerOptions()
                .position(userLatLng)
                .title("${user.name}'s Location")

            googleMap?.addMarker(markerOptions)

            val cameraPosition = CameraPosition.Builder()
                .target(userLatLng)
                .zoom(5.toFloat())
                .build()

            googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }
}
