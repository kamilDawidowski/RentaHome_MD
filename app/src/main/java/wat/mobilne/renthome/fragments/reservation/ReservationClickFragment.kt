package wat.mobilne.renthome.fragments.reservation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_reservation.*
import kotlinx.android.synthetic.main.fragment_reservation_click.*
import wat.mobilne.renthome.R
import wat.mobilne.renthome.adapter.AdapterReservation
import wat.mobilne.renthome.fragments.offer.ItemDetailFragmentArgs
import wat.mobilne.renthome.viewmodel.ReservationViewModel


class ReservationClickFragment : Fragment() {
    lateinit var reservationViewModel: ReservationViewModel
    private val args: ReservationClickFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservation_click, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reservationViewModel = ViewModelProvider(this).get(ReservationViewModel::class.java)
        observeReservation()

        btnCanceReservation.setOnClickListener {
            reservationViewModel.rejectReservation(args.reservationId)
        }
    }

    private fun observeReservation() {
        reservationViewModel.acceptReservationResponse.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
            } else {
            }
        })
        reservationViewModel.rejectReservationResponse.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
            } else {
            }
        })
    }
}