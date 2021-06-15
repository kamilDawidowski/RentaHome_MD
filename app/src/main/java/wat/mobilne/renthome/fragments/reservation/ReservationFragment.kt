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
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_reservation.*
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.adapter.AdapterReservation
import wat.mobilne.renthome.models.ItemDataReservation


class ReservationFragment : Fragment(),AdapterReservation.OnItemClickListener  {

    var offers= generateList()
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

        observeReservation()

        recyclerView2.adapter = AdapterReservation(offers,this)
        recyclerView2.layoutManager = LinearLayoutManager(context)
        recyclerView2.setHasFixedSize(true)
    }
    private fun generateList(): List<ItemDataReservation> {
        val list = ArrayList<ItemDataReservation>()

        for (i in 0 until 5) {

            val item = ItemDataReservation( "Item $i", "19.04.2020")
            list += item
        }

        return list
    }


    @SuppressLint("ResourceAsColor")
    override fun onItemClick(position: Int, currentItem: ItemDataReservation) {
        Toast.makeText(context, currentItem.data, Toast.LENGTH_SHORT).show()
        var user=currentItem.user
        var data=currentItem.data
        // Tutaj wysyłamy powiadominie dla uzytkownika User o potwierdzeniu
    }

    private fun getReservations() {
        val mainActivity = activity as MainActivity
        //mainActivity.viewModel.getReservations()
    }

    private fun observeReservation() {
        val mainActivity = activity as MainActivity
        mainActivity.viewModel.reservationsResponse.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                val reservations = response.body()
                Log.d("Reservation", "Reservations: " + reservations.toString())
            } else {
                Toast.makeText(context, "ERROR: " + response.code(), Toast.LENGTH_SHORT).show()
                // #TODO: Handle server exception
            }
        })
    }



}