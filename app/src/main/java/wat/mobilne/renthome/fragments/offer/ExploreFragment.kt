package wat.mobilne.renthome.fragments.offer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wat.rentahome.models.Offer
import kotlinx.android.synthetic.main.fragment_explore.*
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.adapter.AdapterExplore

class ExploreFragment : Fragment(), AdapterExplore.OnItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        mainActivity.fetchOffers()
        mainActivity.offers?.let {
            recyclerView.adapter = AdapterExplore(it, this)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        floating_action_button.setOnClickListener {

            findNavController().navigate(ExploreFragmentDirections.actionExploreFragmentToAddOfferFragment())
        }
    }

    override fun onItemClick(position: Int, currentItem: Offer) {
        Toast.makeText(context, "${currentItem.title}", Toast.LENGTH_SHORT).show()
        var description = currentItem.description
        var price = currentItem.price.toFloat()
        var title = currentItem.title
        var latitiude = currentItem.latitude.toFloat()
        var longitude = currentItem.longitude.toFloat()
        val action = ExploreFragmentDirections.actionExploreFragmentToItemDetailFragment(
            title,
            description,
            longitude,
            latitiude,
            price
        )
        findNavController().navigate(action)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }


}