package wat.mobilne.renthome.afterLogin.explore.exploreItemDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_item_detail.*
import kotlinx.android.synthetic.main.fragment_item_reservation_detail.*
import wat.mobilne.renthome.R
import java.util.*


class ItemReservationDetailFragment : Fragment() {
    private val args: ItemReservationDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {









        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_reservation_detail, container, false)











    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataPicker=MaterialDatePicker.Builder.dateRangePicker().setTitleText("Pick your reservation Data").build();

        textCashSummary.text = args.price.toString()

// parametry do przekazania
        var data=textSelectedData.setText(dataPicker.headerText);
        var pricr=args.price.toString()






        btnConfirm.setOnClickListener {

            findNavController().navigate(R.id.exploreFragment)


        }
        btnShowData.setOnClickListener {

            dataPicker.show(parentFragmentManager,"DataPicekr");

        }
        btnCancelReservation.setOnClickListener {
            findNavController().navigate(R.id.exploreFragment)
//
        }
        dataPicker.addOnPositiveButtonClickListener {
            textSelectedData.setText(dataPicker.headerText);


            // Respond to positive button click.
        }




    }


}