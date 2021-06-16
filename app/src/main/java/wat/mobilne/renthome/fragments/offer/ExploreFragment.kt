package wat.mobilne.renthome.fragments.offer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import wat.mobilne.renthome.models.Offer
import kotlinx.android.synthetic.main.fragment_explore.*
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.adapter.AdapterExplore
import wat.mobilne.renthome.viewmodel.OfferViewModel

class ExploreFragment : Fragment(), AdapterExplore.OnItemClickListener {
    lateinit var offerViewModel: OfferViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        offerViewModel = ViewModelProvider(this).get(OfferViewModel::class.java)
        observeOfferChange()
        offerViewModel.getOffers()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        floating_action_button.setOnClickListener {
            findNavController().navigate(ExploreFragmentDirections.actionExploreFragmentToAddOfferFragment())
        }
    }

    override fun onItemClick(position: Int, currentItem: Offer) {
        Toast.makeText(context, currentItem.title, Toast.LENGTH_SHORT).show()
        val description = currentItem.description
        val price = currentItem.price.toFloat()
        val title = currentItem.title
        val latitude = currentItem.latitude.toFloat()
        val longitude = currentItem.longitude.toFloat()
        val action = ExploreFragmentDirections.actionExploreFragmentToItemDetailFragment(
            title,
            description,
            longitude,
            latitude,
            price,
            currentItem
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

    private fun observeOfferChange() {
        offerViewModel.getOffersResponse.observe(viewLifecycleOwner, { offers ->
            recyclerView.adapter = offers.body()?.let { AdapterExplore(it, this) }
            recyclerView.adapter?.notifyDataSetChanged()
        })
    }
}