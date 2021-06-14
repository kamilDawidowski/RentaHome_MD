package wat.mobilne.renthome.afterLogin.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*
import wat.mobilne.renthome.R
import wat.mobilne.renthome.withoutLogin.login.LoginFragmentDirections

class ProfileFragment : Fragment() {


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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnUpdateProfile.setOnClickListener {
            chUsername.visibility=View.VISIBLE
            chEmail.visibility=View.VISIBLE
            chName.visibility=View.VISIBLE
            chSurname.visibility=View.VISIBLE
            Toast.makeText(
                context,
                getString(R.string.InCorrectLogin),
                Toast.LENGTH_SHORT
            ).show()
        }
        btnChangePassword.setOnClickListener {
            val action =ProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment()
            findNavController().navigate(action)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}

