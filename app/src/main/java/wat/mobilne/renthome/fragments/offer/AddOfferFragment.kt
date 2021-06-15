package wat.mobilne.renthome.fragments.offer

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.*
import wat.mobilne.renthome.models.Offer
import kotlinx.android.synthetic.main.fragment_add_offer.*
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.utils.Preferences
var lat=0
var long=0
class AddOfferFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient


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
        val mainActivity = activity as MainActivity
        mainActivity.viewModel.createOfferResponse.observe(
            viewLifecycleOwner,
            { response ->
                // When user successfully logged in
                if (response.isSuccessful) {
                    val offer = response.body()!!
                    Log.d("Response", "Created offer: " + response.body().toString())
                    mainActivity.viewModel.getOffers()
                } else {
                    // #TODO: Handle server exception
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
        val mainActivity = activity as MainActivity
        val offer = Offer(
            Preferences.user,
            addTitileOfert.text.toString(),
            addDescriptionOfert.text.toString(),
            2.0,
            2.0,
            2.0,
            null
        )
        Log.d("Offer", offer.toString())
        mainActivity.viewModel.createOffer(offer)
    }

}


