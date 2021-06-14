package wat.mobilne.renthome.afterLogin.addNewOffer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_offer.*
import wat.mobilne.renthome.R
import wat.mobilne.renthome.afterLogin.profile.ProfileFragmentDirections


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

            //Zapisanie zdjęcia
        }
        super.onViewCreated(view, savedInstanceState)
    }




}