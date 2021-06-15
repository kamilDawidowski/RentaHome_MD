package wat.mobilne.renthome.fragments.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_item_reservation_detail.*
import wat.mobilne.renthome.R


class ItemReservationDetailFragment : Fragment() {
    private val args: ItemReservationDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_reservation_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataPicker=MaterialDatePicker.Builder.dateRangePicker().setTitleText("Pick your reservation Data").build()

        textCashSummary.text = args.price.toString()
        //var data=textSelectedData.setText(dataPicker.headerText);
        var pricr=args.price.toString()

        btnConfirm.setOnClickListener {
            findNavController().navigate(R.id.exploreFragment)
        }

        btnShowData.setOnClickListener {
            dataPicker.show(parentFragmentManager,"DataPicker")
        }

        btnCancelReservation.setOnClickListener {
            findNavController().navigate(R.id.exploreFragment)
        }
        dataPicker.addOnPositiveButtonClickListener {
            textSelectedData.text = dataPicker.headerText
        }




    }


}