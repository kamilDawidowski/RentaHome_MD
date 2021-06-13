package wat.mobilne.renthome.withoutLogin.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wat.rentahome.models.Registration
import kotlinx.android.synthetic.main.fragment_regiser.*
import kotlinx.android.synthetic.main.fragment_regiser.inputPassword
import okhttp3.Credentials
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.utils.Preferences
import wat.mobilne.renthome.withoutLogin.login.LoginFragmentDirections


class RegiserFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_regiser, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeRegister()

        button_Register.setOnClickListener() {
            onRegisterButtonClick()
        }

        btnToLogin.setOnClickListener() {
            onButtonToLoginClick()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onButtonToLoginClick() {
        navigateToLogin()
    }

    private fun onRegisterButtonClick() {
        tryRegister()
    }

    private fun navigateToLogin() {
        val action = RegiserFragmentDirections.actionRegiserFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun observeRegister() {
        val mainActivity = activity as MainActivity
        mainActivity.viewModel.registerResponse.observe(mainActivity, Observer { response ->
            if (response.isSuccessful) {
                navigateToLogin()
            } else {
                // #TODO: Handle server exception
            }
        })
    }

    private fun tryRegister() {
        val mainActivity = activity as MainActivity
        val registrationData = Registration(
            inpuUsername.text.toString(),
            inpuUsername.text.toString(),
            inpuUsername.text.toString(),
            inputEmail.text.toString(),
            inputPassword.text.toString())
        mainActivity.viewModel.register(registrationData)
    }


}