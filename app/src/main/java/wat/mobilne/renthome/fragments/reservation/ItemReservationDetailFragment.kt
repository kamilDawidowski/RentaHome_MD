package wat.mobilne.renthome.fragments.reservation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.fragment_item_reservation_detail.*
import wat.mobilne.renthome.MainViewModel
import wat.mobilne.renthome.R
import wat.mobilne.renthome.adapter.AdapterExplore
import wat.mobilne.renthome.models.Reservation
import wat.mobilne.renthome.utils.Preferences
import wat.mobilne.renthome.viewmodel.OfferViewModel
import wat.mobilne.renthome.viewmodel.ReservationViewModel
import java.util.*


class ItemReservationDetailFragment : Fragment() {
    private val args: ItemReservationDetailFragmentArgs by navArgs()
    lateinit var reservationViewModel: ReservationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_reservation_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Safeargs", args.offer.toString())

        reservationViewModel = ViewModelProvider(this).get(ReservationViewModel::class.java)
        observeReservation()

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
            makeReservation(Date(it.first), Date(it.second))
        }
    }

    private fun makeReservation(startDate: Date, endDate: Date) {
        val reservation = Reservation(args.offer, Preferences.user, null,null,null)//startDate, endDate, null)
        reservationViewModel.makeReservation(reservation)
    }

    private fun observeReservation() {
        reservationViewModel.reservationsResponse.observe(viewLifecycleOwner, { reservation ->
            Log.d("Reservation", reservation.toString())
        })
    }


}