package wat.mobilne.renthome.fragments.management

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_change_password.*
import wat.mobilne.renthome.R
import wat.mobilne.renthome.utils.Preferences
import wat.mobilne.renthome.viewmodel.UserViewModel


class ChangePasswordFragment : Fragment() {
    lateinit var userViewModel: UserViewModel
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
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        observerPasswordChange()


        super.onViewCreated(view, savedInstanceState)
        buttonConfirmNewPassword.setOnClickListener {
            if (validateForm(inputPassword.text.toString(),inputConfirmChangedPassword.text.toString())) {
                userViewModel.changePassword(inputPassword.text.toString())
            }
        }

        buttonCancelnewPassword.setOnClickListener {
            findNavController().navigate(R.id.exploreFragment)

        }
    }

    private fun observerPasswordChange() {
        userViewModel.changePasswordResponse.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                findNavController().navigate(R.id.exploreFragment)
            } else {
                Toast.makeText(context, "ERROR: " + response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun validateForm( password: String?,passwordConfirm:String?): Boolean {

        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        val isValidConfirmPassword = password != null && password.isNotBlank() && password.length >= 6 && password==passwordConfirm
        return  isValidPassword &&  isValidConfirmPassword
    }

}