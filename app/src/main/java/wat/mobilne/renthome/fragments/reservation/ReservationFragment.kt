package wat.mobilne.renthome.fragments.reservation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list_item_reservation.*
import kotlinx.android.synthetic.main.fragment_list_item_reservation.view.*
import kotlinx.android.synthetic.main.fragment_reservation.*
import wat.mobilne.renthome.R
import wat.mobilne.renthome.adapter.AdapterReservation
import wat.mobilne.renthome.fragments.management.LoginFragmentDirections
import wat.mobilne.renthome.fragments.offer.ExploreFragmentDirections
import wat.mobilne.renthome.fragments.offer.ItemDetailFragmentDirections
import wat.mobilne.renthome.models.Offer
import wat.mobilne.renthome.models.Reservation
import wat.mobilne.renthome.utils.Preferences
import wat.mobilne.renthome.viewmodel.ReservationViewModel
import java.util.stream.Collectors


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
    }


    @SuppressLint("ResourceAsColor")
    override fun onItemClick(position: Int, currentItem: Reservation) {
        Toast.makeText(context, "Click",Toast.LENGTH_SHORT)

        val action = currentItem.id?.let {
            ReservationFragmentDirections.actionReservationFragmentToReservationClickFragment(
                it
            )
        }
        action?.let { findNavController().navigate(it) }
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