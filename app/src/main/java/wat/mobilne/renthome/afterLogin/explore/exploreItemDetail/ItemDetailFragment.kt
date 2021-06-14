package wat.mobilne.renthome.afterLogin.explore.exploreItemDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_item_detail.*
import wat.mobilne.renthome.R

class ItemDetailFragment : Fragment() {

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



        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnReservation.setOnClickListener {

            val action=ItemDetailFragmentDirections.actionItemDetailFragmentToItemReservationDetailFragment()
            findNavController().navigate(action)


        }





        btnCancel.setOnClickListener {
            val action=ItemDetailFragmentDirections.actionItemDetailFragmentToProfileFragment()
            findNavController().navigate(action)
        }
    }





}