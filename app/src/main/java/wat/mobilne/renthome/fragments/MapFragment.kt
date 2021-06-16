package wat.mobilne.renthome.fragments

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_explore.*
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.adapter.AdapterExplore
import wat.mobilne.renthome.viewmodel.OfferViewModel


// TODO: Rename parameter arguments, choose names that match

class MapFragment : Fragment(), GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener,
    OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var map: GoogleMap
    private lateinit var offerViewModel: OfferViewModel

    companion object {
        private const val LOCATION_REQUST_CODE = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->

        offerViewModel = ViewModelProvider(this).get(OfferViewModel::class.java)
        observeOffersChange(googleMap)
        offerViewModel.getOffers()

        googleMap.uiSettings.isZoomControlsEnabled = true

//            if(args.orZoom)
//            {
//                val pointZoom=LatLng(args.lat.toDouble(),args.long.toDouble())
//                googleMap.moveCamera(CameraUpdateFactory.newLatLng(pointZoom))
//            }

    }




    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onMyLocationClick(p0: Location) {

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap) {
        map.isMyLocationEnabled = true
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
    }

    private fun enableMyLocation() {
        if (!::map.isInitialized) return
        if (context?.let { ContextCompat.checkSelfPermission(it, ACCESS_FINE_LOCATION) }
            == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        } else {
            // Permission to access the location is missing. Show rationale and request permission

        }
    }


    override fun onMyLocationButtonClick(): Boolean {
        TODO("Not yet implemented")
    }

    private fun observeOffersChange(googleMap: GoogleMap) {
        offerViewModel.getOffersResponse.observe(viewLifecycleOwner, { offers ->
            offers.body()?.forEach {
                val point = LatLng(it.latitude, it.longitude)
                val icon = BitmapFactory.decodeResource(
                    context?.resources,
                    R.drawable.ic_home
                )

                googleMap.addMarker(
                    MarkerOptions().position(point).title(it.title)
                        .snippet("Cena: " + it.price.toString()).icon(context?.let { it1 ->
                            bitmapDescriptorFromVector(
                                it1, R.drawable.ic_home
                            )
                        }
                        )
                )
            }
        })
    }


}
