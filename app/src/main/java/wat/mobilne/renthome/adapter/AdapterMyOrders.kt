package wat.mobilne.renthome.adapter

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import wat.mobilne.renthome.models.Offer
//import wat.mobilne.renthome.R
//
//class AdapterMyOrders (
//    // Lista naszych elementów do wyswietlenia
//    private val itemList: List<Offer>,
//    private val listener: OnItemClickListener,
//
//    ) :
//    RecyclerView.Adapter<AdapterExplore.ExampleViewHolder>() {
//    var itemFilterList: List<Offer>
//
//
//    override fun getItemCount() = itemFilterList.size
//
//
//    init {
//        itemFilterList=itemList
//    }
//
//
//    override fun onBindViewHolder(holder: AdapterExplore.ExampleViewHolder, position: Int) {
//        val currentItem = itemList[position]
//        holder.imageView.setImageResource(R.drawable.ic_explore)
//        holder.itemCash.text = currentItem.price.toString()
//        holder.itemTitle.text = currentItem.title
//
//
//        val ofetHolder=holder as ExampleViewHolder
//
//    }
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(
//            R.layout.list_item_explore,
//            parent, false
//        )
//        return ExampleViewHolder(itemView)
//    }
//
//
//    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
//        View.OnClickListener {
//        val imageView: ImageView = itemView.itemImage
//        val itemCash: TextView = itemView.itemCash
//        val itemTitle: TextView = itemView.itemTitle
//
//        //        val textView3: TextView = itemView.textView_DescriptionRow
//        init {
//            itemView.setOnClickListener(this)
//
//        }
//
//        override fun onClick(v: View?) {
//            val position: Int = absoluteAdapterPosition
//            val currentItem = itemList[position]
//
//            if (position != RecyclerView.NO_POSITION) {
//                listener.onItemClick(position, currentItem)
//            }
//
//        }
//    }
//
//    interface OnItemClickListener {
//        fun onItemClick(position: Int, currentItem: Offer)
//    }
//
//
//
//    // funckje do filtrowania
//
//}
