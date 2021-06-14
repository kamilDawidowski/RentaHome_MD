package wat.mobilne.renthome.afterLogin.explore

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_explore.*
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
        val offers = generateDummyList(7)
        recyclerView.adapter = AdapterExplore(offers,this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        floating_action_button.setOnClickListener {
            val action = ExploreFragmentDirections.actionExploreFragmentToAddOfferFragment()
            findNavController().navigate(action)
            Toast.makeText(
                context,
                "sss",
                Toast.LENGTH_SHORT
            ).show();
        }

        floating_action_button_mapa.setOnClickListener {

            findNavController().navigate(R.id.profileFragment)
            Toast.makeText(
                context,
                "ddd",
                Toast.LENGTH_SHORT
            ).show();

        }

        floating_action_button_profile.setOnClickListener {
//                val action = ExploreFragmentDirections.actionExploreFragmentToProfileFragment()
//                findNavController().navigate(action)
            Toast.makeText(
                context,
                "dd",
                Toast.LENGTH_SHORT
            ).show();
        }
    }







    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onItemClick(position: Int, currentItem:ItemData) {
        Toast.makeText(context, "fff" , Toast.LENGTH_SHORT).show()
        val action = ExploreFragmentDirections.actionExploreFragmentToItemDetailFragment()
        findNavController().navigate(action)

    }

}
// Generowanie przykładowych elementów
private fun generateDummyList(size: Int): List<ItemData> {
    val list = ArrayList<ItemData>()
    for (i in 0 until size) {
        val drawable = when (i % 3) {
            0 -> R.drawable.ic_profile
            1 -> R.drawable.ic_map
            else -> R.drawable.ic_explore
        }
        val item = ItemData(drawable, 4, "Line 2","ddd",5.0,5.0)
        list += item
    }
    return list
}