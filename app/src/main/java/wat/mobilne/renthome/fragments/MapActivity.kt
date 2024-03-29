package wat.mobilne.renthome.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import wat.mobilne.renthome.R

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions


class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClien: FusedLocationProviderClient
    companion object{
        private const val LOCATION_REQUESTE_CODE=1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map4) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClien = LocationServices.getFusedLocationProviderClient(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setUpMap()


    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUESTE_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClien.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLong)
                val x=intent.getDoubleExtra("aa",0.0)
                val y=intent.getDoubleExtra("bb",0.0)
                val second=LatLng(x,y)
                placeMarkerOnMap(second)
                val polyline1 = mMap.addPolyline(
                    PolylineOptions()
                        .clickable(true)
                        .add(
                            second,
                            currentLatLong))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))

            }
            else
            {
                Toast.makeText(this,"Brak", Toast.LENGTH_LONG).show()
            }
            Toast.makeText(this,getString(R.string.lokalization_is_granted), Toast.LENGTH_LONG).show()
        }
    }

    private fun placeMarkerOnMap(currentLatLong: LatLng) {
        var markerOptions = MarkerOptions().position(currentLatLong)



        markerOptions.title("$currentLatLong")

        mMap.addMarker((markerOptions))
    }

    override fun onMarkerClick(p0: Marker?): Boolean = false
}
