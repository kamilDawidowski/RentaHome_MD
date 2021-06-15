package wat.mobilne.renthome.fragments

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
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R


// TODO: Rename parameter arguments, choose names that match

class MapFragment : Fragment(), GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener,
    OnMapReadyCallback {

    private val REQUEST_LOCATION_PERMISSION = 1
//    private val args: MapFragmentArgs by navArgs()

    private fun isPermissionGranted() : Boolean {
        return ContextCompat.checkSelfPermission(
            activity as MainActivity,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )==PackageManager.PERMISSION_GRANTED
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    private val callback = OnMapReadyCallback { googleMap ->



        val menuActivity = activity as MainActivity

        val offers = menuActivity.offers

        //Dodawanie znaczników ofert
        if (offers != null) {
            offers.value?.forEach {
                val point = LatLng(it.latitude, it.longitude)
                val icon = BitmapFactory.decodeResource(
                    context?.resources,
                    R.drawable.ic_home
                )


                googleMap.addMarker(
                    MarkerOptions().position(point).title(it.title)
                        .snippet("Cena: " + it.price.toString()).icon(context?.let { it1 ->
                            bitmapDescriptorFromVector(
                                it1,R.drawable.ic_home)
                        }
                        )
                )
    //
            }
//            if(args.orZoom)
//            {
//                val pointZoom=LatLng(args.lat.toDouble(),args.long.toDouble())
//                googleMap.moveCamera(CameraUpdateFactory.newLatLng(pointZoom))
//            }

        }


    }


private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, vectorResId)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
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
