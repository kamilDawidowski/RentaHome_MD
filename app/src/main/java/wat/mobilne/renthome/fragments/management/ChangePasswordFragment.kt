package wat.mobilne.renthome.fragments.management

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_change_password.*
import wat.mobilne.renthome.R


class ChangePasswordFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        buttonConfirmNewPassword.setOnClickListener {
            validateForm(inputPassword.text.toString(),inputConfirmChangedPassword.text.toString())

            // zmiana hasła trzeba wysłac puta!!!!!!!!!!!
            findNavController().navigate(R.id.exploreFragment)
        }

        buttonCancelnewPassword.setOnClickListener {
            findNavController().navigate(R.id.exploreFragment)

        }
    }


    private fun validateForm( password: String?,passwordConfirm:String?): Boolean {

        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        val isValidConfirmPassword = password != null && password.isNotBlank() && password.length >= 6 && password==passwordConfirm
        return  isValidPassword &&  isValidConfirmPassword
    }

}