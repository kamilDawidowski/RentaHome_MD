package wat.mobilne.renthome.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list_item_reservation.view.*
import wat.mobilne.renthome.R
import wat.mobilne.renthome.models.ItemDataReservation
import wat.mobilne.renthome.fragments.reservation.ReservationFragment

class AdapterReservation(

    // Lista naszych elementów do wyswietlenia
    private val itemList: List<ItemDataReservation>,
    private val listener: ReservationFragment,



    ) :
    RecyclerView.Adapter<AdapterReservation.ExampleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterReservation.ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_list_item_reservation,
            parent, false
        )
        return ExampleViewHolder(itemView)
    }



    override fun getItemCount() = itemList.size
    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val itemData: TextView = itemView.tReservatioData
        val itemUser: TextView = itemView.tReservatioUser

        //        val textView3: TextView = itemView.textView_DescriptionRow
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = getAbsoluteAdapterPosition()
            val currentItem = itemList[position]

            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, currentItem)
            }

        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, currentItem: ItemDataReservation)
    }

    override fun onBindViewHolder(holder: AdapterReservation.ExampleViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemData.text = currentItem.data
        holder.itemUser.text = currentItem.user

    }



}
