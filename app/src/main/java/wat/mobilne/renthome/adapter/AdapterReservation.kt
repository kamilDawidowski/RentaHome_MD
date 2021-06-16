package wat.mobilne.renthome.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list_item_reservation.*
import kotlinx.android.synthetic.main.fragment_list_item_reservation.view.*
import org.w3c.dom.Text
import wat.mobilne.renthome.R
import wat.mobilne.renthome.models.ItemDataReservation
import wat.mobilne.renthome.fragments.reservation.ReservationFragment
import wat.mobilne.renthome.fragments.reservation.ReservationFragmentDirections
import wat.mobilne.renthome.models.Reservation

class AdapterReservation(
    // Lista naszych elementów do wyswietlenia
    private val itemList: List<Reservation>,
    private val listener: ReservationFragment,
) :

    RecyclerView.Adapter<AdapterReservation.ExampleViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterReservation.ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_list_item_reservation,
            parent, false
        )
        return ExampleViewHolder(itemView)
    }


    override fun getItemCount() = itemList.size
    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val itemOffer: TextView = itemView.tOfferDataInReservation
        val itemDate: TextView = itemView.tReservatioData
        val itemUser: TextView = itemView.tReservatioUser


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = absoluteAdapterPosition
            val currentItem = itemList[position]

            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, currentItem)
            }

        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, currentItem: Reservation)
    }

    override fun onBindViewHolder(holder: AdapterReservation.ExampleViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemOffer.text = currentItem.offerDto.title
        holder.itemDate.text = currentItem.endDate
        holder.itemUser.text = currentItem.userDto.username
    }
}
