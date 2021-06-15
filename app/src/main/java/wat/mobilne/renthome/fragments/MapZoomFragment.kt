package wat.mobilne.renthome.fragments

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import wat.mobilne.renthome.R


class MapZoomFragment : Fragment() , GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener,
    OnMapReadyCallback {
    var  REQUEST_CODE_LOCATION_PERMISON=1
    private val args: MapZoomFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->
                val pointZoom=LatLng(args.lat.toDouble(), args.long.toDouble())
                googleMap.addMarker(
                    MarkerOptions().position(pointZoom).title(getString(R.string.your_choice)).icon(
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                    )
                )
        val cameraPosition = CameraPosition.Builder()
            .target(pointZoom)
            .zoom(7f).build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        googleMap.isTrafficEnabled = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map2) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_zoom, container, false)
    }

    override fun onMyLocationClick(p0: Location) {
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap) {
        // TODO: Before enabling the My Location layer, you must request
        // location permission from the user. This sample does not include
        // a request for location permission.
        map.isMyLocationEnabled = true
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
    }

    override fun onMyLocationButtonClick(): Boolean {
        TODO("Not yet implemented")
    }


}
