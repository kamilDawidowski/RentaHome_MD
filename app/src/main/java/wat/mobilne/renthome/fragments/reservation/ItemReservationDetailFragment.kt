package wat.mobilne.renthome.fragments.reservation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.fragment_item_reservation_detail.*
import wat.mobilne.renthome.R
import wat.mobilne.renthome.models.Reservation
import wat.mobilne.renthome.utils.Preferences
import wat.mobilne.renthome.viewmodel.ReservationViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class ItemReservationDetailFragment : Fragment() {
    private val args: ItemReservationDetailFragmentArgs by navArgs()
    lateinit var reservationViewModel: ReservationViewModel
    var startDate: String? = null
    var endDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_reservation_detail, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Log.d("Safeargs", args.offer.toString())

        reservationViewModel = ViewModelProvider(this).get(ReservationViewModel::class.java)
        observeReservation()

        val dataPicker=MaterialDatePicker.Builder.dateRangePicker().setTitleText("Pick your reservation Data").build()
        textCashSummary.text = args.price.toString()
        //var data=textSelectedData.setText(dataPicker.headerText);
        var pricr=args.price.toString()

        btnConfirm.setOnClickListener {
            makeReservation()
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
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            startDate = sdf.format(Date(it.first))
            endDate = sdf.format(Date(it.second))
        }
    }

    private fun makeReservation() {
        if (startDate != null && endDate != null) {
            val reservation = Reservation(args.offer, Preferences.user, startDate!!, endDate!!)
            reservationViewModel.makeReservation(reservation)
        }
    }

    private fun observeReservation() {
        reservationViewModel.makeReservationResponse.observe(viewLifecycleOwner, { reservation ->
            Log.d("Reservation", reservation.toString())
        })
    }


}