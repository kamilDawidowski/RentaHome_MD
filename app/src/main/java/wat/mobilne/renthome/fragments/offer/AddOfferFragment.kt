package wat.mobilne.renthome.fragments.offer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_offer.*
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.utils.Preferences


class AddOfferFragment : Fragment() {

    private val REQUEST_PERMISSION = 100
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PICK_IMAGE = 2
    val REQUEST_CODE = 100

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

        buttonAddOfert.setOnClickListener {

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
        mainActivity.viewModel.offersResponse.observe(viewLifecycleOwner, Observer { response ->
            // When user successfully logged in
            if (response.isSuccessful) {
                val offer = response.body()!!
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




}