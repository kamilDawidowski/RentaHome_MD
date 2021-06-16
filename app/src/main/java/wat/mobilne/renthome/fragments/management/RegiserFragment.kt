package wat.mobilne.renthome.fragments.management

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import wat.mobilne.renthome.models.Registration
import kotlinx.android.synthetic.main.fragment_regiser.*
import kotlinx.android.synthetic.main.fragment_regiser.inputPassword
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.viewmodel.RegisterViewModel
import wat.mobilne.renthome.viewmodel.UserViewModel


class RegiserFragment : Fragment() {
    lateinit var registerViewModel: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_regiser, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        observeRegister()
        (activity as MainActivity).hideBottomMenu()

        button_Register.setOnClickListener {
            onRegisterButtonClick()
        }

        btnBacktoLogin.setOnClickListener {
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
            Toast.makeText(context,inpuUsername.text.toString() ,Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
            inpuUsername.setText("")
            inputPassword.setText("")
            inputPasswordConfirm.setText("")
            inputEmail.setText("")
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
        findNavController().navigate(R.id.loginFragment)

    }



    private fun tryRegister() {
        val registrationData = Registration(
            inpuUsername.text.toString(),
            inpuUsername.text.toString(),
            inpuUsername.text.toString(),
            inputEmail.text.toString(),
            inputPassword.text.toString())

        registerViewModel.register(registrationData)
    }
    private fun observeRegister() {
        registerViewModel.registerResponse.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                navigateToLogin()
            } else {
                Toast.makeText(context, "ERROR: " + response.code(), Toast.LENGTH_SHORT).show()
                // #TODO: Handle server exception
            }
        })
    }

    private fun validateForm(email: String?, password: String?,passwordConfirm:String?,username:String?): Boolean {
        val isValidEmail = email != null && email.isNotBlank() && email.contains("@")
        val isUsernameValid=username !=null && username.isNotBlank()&& username.length>=5
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        val isValidConfirmPassword = password != null && password.isNotBlank() && password.length >= 6 && password==passwordConfirm
        return isValidEmail && isValidPassword && isUsernameValid && isValidConfirmPassword
    }



}