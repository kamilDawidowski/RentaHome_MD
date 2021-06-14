package wat.mobilne.renthome.withoutLogin.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import okhttp3.Credentials
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.utils.Preferences


class LoginFragment : Fragment() {
    private var loginCorrectFlag:Boolean=false//Trzeba ustawić to zmienna by zalogowac sie na true w isValidateData funkcji sie to wykonuje
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLogin()

        loginButton.setOnClickListener() {


            if(validateForm(inputUsername.text.toString(),inputPassword.text.toString())) {

                inputUsername.setText("")
               inputPassword.setText("")
                val action = LoginFragmentDirections.actionLoginFragmentToExploreFragment()
                findNavController().navigate(action)

                Toast.makeText(
                    context,
                    getString(R.string.Login_Correct),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else
            {
                Toast.makeText(
                    context,
                    getString(R.string.InCorrectLogin),
                    Toast.LENGTH_SHORT
                ).show()
            }










//            onLoginButtonClick()
        }

        textViewSignUp.setOnClickListener() {
            val action = LoginFragmentDirections.actionLoginFragmentToRegiserFragment()
            findNavController().navigate(action)
        }
    }

    private fun navigateToExplore() {
        val action = LoginFragmentDirections.actionLoginFragmentToExploreFragment()
        findNavController().navigate(action)
        Toast.makeText(context,inputUsername.text.toString() ,Toast.LENGTH_SHORT).show();
    }

    private fun onLoginButtonClick() {
        tryLogin()
    }

    private fun tryLogin() {
        val mainActivity = activity as MainActivity
        Preferences.basicToken = Credentials.basic(inputUsername.text.toString(), inputPassword.text.toString())
        mainActivity.viewModel.getUser()
    }

    private fun observeLogin() {
        val mainActivity = activity as MainActivity
        mainActivity.viewModel.userResponse.observe(mainActivity, Observer { response ->
            if (response.isSuccessful) {
                Preferences.user = response.body()!!
                mainActivity.viewModel.getOffers()
                navigateToExplore()
            } else {
                // #TODO: Handle server exception
            }
        })
    }
    private fun validateForm(email: String?, password: String?): Boolean {
        val isValidEmail = email != null && email.isNotBlank() && email.contains("@")
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        return isValidEmail && isValidPassword
    }
    ////////////// Nowe



}
