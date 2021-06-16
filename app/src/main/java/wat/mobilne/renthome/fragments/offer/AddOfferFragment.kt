package wat.mobilne.renthome.fragments.offer

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.*
import wat.mobilne.renthome.models.Offer
import kotlinx.android.synthetic.main.fragment_add_offer.*
import kotlinx.android.synthetic.main.fragment_explore.*
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.adapter.AdapterExplore
import wat.mobilne.renthome.utils.Preferences
import wat.mobilne.renthome.viewmodel.OfferViewModel

var lat=0
var long=0
class AddOfferFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var offerViewModel: OfferViewModel


    val REQUEST_CODE = 100
    var PERMISSION_ID = 44


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_offer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        offerViewModel = ViewModelProvider(this).get(OfferViewModel::class.java)
        observeCreateOffer()
        buttonAddOfert.setOnClickListener {
            createOffer()
            // Dodanie zmiennych do ogłoszenia
            addDescriptionOfert.text
            addPriceOfert.text
            addTitileOfert.text

            findNavController().navigate(R.id.exploreFragment)
        }

        buttonAddImage.setOnClickListener {
            openGalleryForImage()
            //Zapisanie zdjęcia
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeCreateOffer() {
        offerViewModel.createOfferResponse.observe(viewLifecycleOwner, { offers ->
            if (offers.isSuccessful) {
                findNavController().navigate(R.id.exploreFragment)
            } else {
            }
        })
    }
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            imageViewAddAdvertisment.setImageURI(data?.data) // handle chosen image
        }
    }
    private fun createOffer() {
        val offer = Offer(
            null,
            Preferences.user,
            addTitileOfert.text.toString(),
            addDescriptionOfert.text.toString(),
            2.0,
            2.0,
            2.0,
        )
        Log.d("Offer", offer.toString())
        offerViewModel.createOffer(offer)
    }

}


