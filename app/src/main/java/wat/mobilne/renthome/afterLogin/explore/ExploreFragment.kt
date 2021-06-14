package wat.mobilne.renthome.afterLogin.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_explore.*
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.adapter.AdapterExplore

class ExploreFragment : Fragment(),AdapterExplore.OnItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// Przykładowa lista dla elementów
       var offers= generateList()
        recyclerView.adapter = AdapterExplore(offers,this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        floating_action_button.setOnClickListener {

            findNavController().navigate(R.id.itemDetailFragment)
        }
        floating_action_button_mapa.setOnClickListener {

            findNavController().navigate(R.id.mapFragment)
        }
        floating_action_button_profile.setOnClickListener {

            findNavController().navigate(R.id.profileFragment)
        }


        }

    override fun onItemClick(position: Int, currentItem: ItemData) {
        Toast.makeText(context, "${currentItem.title}" , Toast.LENGTH_SHORT).show()
        var description=currentItem.decribe
        var price=currentItem.price.toFloat()
        var title=currentItem.title
        var latitiude=currentItem.latitude.toFloat()
        var longitude=currentItem.longitude.toFloat()


        val action = ExploreFragmentDirections.actionExploreFragmentToItemDetailFragment(title,description,longitude,latitiude,price)
        findNavController().navigate(action)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }
    private fun generateList(): List<ItemData> {
        val list = ArrayList<ItemData>()
        val mainActivity = activity as MainActivity
        val result=mainActivity.offers

        if (result != null) {
            result.forEach {
                val row=ItemData(
                    R.drawable.ic_explore,
                    it.price,
                    it.title,
                    it.description,
                    it.latitude,
                    it.longitude
                )
                list.add(row)
            }
        }




        return list
    }


}