package wat.mobilne.renthome.fragments.reservation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_item_reservation.*
import kotlinx.android.synthetic.main.fragment_reservation.*
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.adapter.AdapterReservation
import wat.mobilne.renthome.models.ItemDataReservation
import wat.mobilne.renthome.models.Reservation
import wat.mobilne.renthome.viewmodel.OfferViewModel
import wat.mobilne.renthome.viewmodel.ReservationViewModel


class ReservationFragment : Fragment(),AdapterReservation.OnItemClickListener  {
    lateinit var reservationViewModel: ReservationViewModel
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
        return inflater.inflate(R.layout.fragment_reservation, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reservationViewModel = ViewModelProvider(this).get(ReservationViewModel::class.java)
        observeReservation()
        getReservations()

        btnAcceptReservation.setOnClickListener {
            onReservationAccepted()
        }
        btnRejectReservation.setOnClickListener {
            onReservationRejected()
        }

    }

    @SuppressLint("ResourceAsColor")
    override fun onItemClick(position: Int, currentItem: Reservation) {
    }


    private fun getReservations() {
        reservationViewModel.getReservations()
    }

    private fun observeReservation() {
        reservationViewModel.reservationsResponse.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                val reservations = response.body()
                Log.d("Reservation", "Reservations: " + reservations.toString())
                recyclerView2.adapter = reservations?.let { AdapterReservation(it,this) }
                recyclerView2.layoutManager = LinearLayoutManager(context)
                recyclerView2.setHasFixedSize(true)
            } else {
                Toast.makeText(context, "ERROR: " + response.code(), Toast.LENGTH_SHORT).show()
                // #TODO: Handle server exception
            }
        })
    }

    private fun onReservationAccepted() {
//        reservationViewModel.acceptReservation()
    }

    private fun onReservationRejected() {

    }



}