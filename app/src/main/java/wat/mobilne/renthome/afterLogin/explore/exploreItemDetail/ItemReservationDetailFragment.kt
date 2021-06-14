package wat.mobilne.renthome.afterLogin.explore.exploreItemDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_item_reservation_detail.*
import wat.mobilne.renthome.R
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemReservationDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemReservationDetailFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {









        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_reservation_detail, container, false)











    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataPicker=MaterialDatePicker.Builder.dateRangePicker().setTitleText("Pick your reservation Data").build();



        btnConfirm.setOnClickListener {
            val action=ItemReservationDetailFragmentDirections.actionItemReservationDetailFragmentToExploreFragment()
            findNavController().navigate(action)
            Toast.makeText(
                context,
                getString(R.string.confirm),
                Toast.LENGTH_SHORT
            ).show();

        }
        btnShowData.setOnClickListener {

            dataPicker.show(parentFragmentManager,"DataPicekr");

        }
        btnCancelReservation.setOnClickListener {
            val action=ItemReservationDetailFragmentDirections.actionItemReservationDetailFragmentToExploreFragment()
            findNavController().navigate(action)

        }
        dataPicker.addOnPositiveButtonClickListener {
            textSelectedData.setText(dataPicker.headerText);

            // Respond to positive button click.
        }




    }


}