package wat.mobilne.renthome.withoutLogin.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wat.rentahome.models.Registration
import kotlinx.android.synthetic.main.fragment_regiser.*
import kotlinx.android.synthetic.main.fragment_regiser.inputPassword
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R


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

        btnBacktoLogin.setOnClickListener() {
            onButtonToLoginClick()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onButtonToLoginClick() {
        navigateToLogin()
    }

    private fun onRegisterButtonClick() {

        if(validateForm(inputEmail.text.toString(),inputPassword.text.toString(),inputPasswordConfirm.text.toString(),inpuUsername.text.toString()))
        {
            tryRegister()
//            val action = RegiserFragmentDirections.actionRegiserFragmentToLoginFragment()
//            inpuUsername.setText("")
//            inputPassword.setText("")
//            inputPasswordConfirm.setText("")
//            inputEmail.setText("")
//            findNavController().navigate(action)

        }
        else
        {
            Toast.makeText(
                context,
               R.string.hintincorecpasssword,
                Toast.LENGTH_SHORT
            ).show()

        }

    }

    private fun navigateToLogin() {
//        val action = RegiserFragmentDirections.actionRegiserFragmentToLoginFragment()
//        findNavController().navigate(action)
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

    private fun validateForm(email: String?, password: String?,passwordConfirm:String?,username:String?): Boolean {
        val isValidEmail = email != null && email.isNotBlank() && email.contains("@")
        val isUsernameValid=username !=null && username.isNotBlank()&& username.length>=5
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        val isValidConfirmPassword = password != null && password.isNotBlank() && password.length >= 6 && password==passwordConfirm
        return isValidEmail && isValidPassword && isUsernameValid && isValidConfirmPassword
    }



}