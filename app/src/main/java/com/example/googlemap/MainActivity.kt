package com.example.googlemap

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

private const val PERMISSION_REQUEST = 10

class MainActivity : AppCompatActivity() {

    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap
    private var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissions, PERMISSION_REQUEST)
                    setContentView(R.layout.activity_main)
                }
            }
            googleMap.isMyLocationEnabled = true
            val location2 = LatLng(9.89, 78.11)
            googleMap.addMarker(MarkerOptions().position(location2).title("Madurai"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location2, 5f))

            val location1 = LatLng(13.03, 77.60)
            googleMap.addMarker(MarkerOptions().position(location1).title("My location"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1, 5f))

        })
    }
}