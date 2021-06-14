package wat.mobilne.renthome.fragments.offer

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_item_detail.*
import wat.mobilne.renthome.R
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.*

class ItemDetailFragment : Fragment() {
    private val args: ItemDetailFragmentArgs by navArgs()
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



        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var long = args.long
        var lat = args.lat

        var city=getAddress(lat.toDouble(),long.toDouble())
//Przekazywanie parametrów zadaeklarowanych wczesniej w naawigacji
        textLocalization.text=city
        textTitle.text = "tytuljack."//args.title
        textDescription.text = args.description
        textCash.text = args.price.toString()






        btnReservation.setOnClickListener {

            val action=ItemDetailFragmentDirections.actionItemDetailFragmentToItemReservationDetailFragment(args.price)
            findNavController().navigate(action)


        }





        btnCancel.setOnClickListener {
//            val action=ItemDetailFragmentDirections.actionItemDetailFragmentToExploreFragment()
//            findNavController().navigate(action)
        }
    }

    private fun getAddress(latitude: Double, longitude: Double): String? {
        val result = StringBuilder()
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if((latitude < -90.0 || latitude > 90.0) || (longitude < -180.0 || longitude > 180.0))
            {
                result.append("Błąd").append("\n")
                result.append("Błąd")
            }
            else
            {
                if (addresses.size > 0) {
                    val address = addresses[0]
                    if (address.locality==null)
                    {
                        result.append("Brak dokłdnej ulicy, ").append("\n")
                    }else
                    {
                        result.append(address.locality).append("\n")
                    }

                    result.append(address.countryName)
                }

            }


        } catch (e: IOException) {
            Log.e("tag","mApaaaaaaaaaaaaa")
        }
        catch (b: IllegalArgumentException)
        {
            result.append("Brak dokładnego adresu").append("\n")
            result.append("brak dokładnego adresu ")
        }
        return result.toString()
    }




}