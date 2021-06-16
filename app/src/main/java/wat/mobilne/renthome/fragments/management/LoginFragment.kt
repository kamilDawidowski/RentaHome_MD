package wat.mobilne.renthome.fragments.management

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import okhttp3.Credentials
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.utils.Preferences
import wat.mobilne.renthome.viewmodel.UserViewModel


class LoginFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).hideBottomMenu()
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Set observer to wait for user to log in.
        observeLogin()

        loginButton.setOnClickListener {
            if(validateForm(inputUsername.text.toString(), inputPassword.text.toString())) {
                onLoginButtonClick()
            }
        }

        textViewSignUp.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegiserFragment()
            navController.navigate(action)

        }
    }

    private fun navigateToExplore() {
        val action = LoginFragmentDirections.actionLoginFragmentToExploreFragment()
        navController.navigate(action)
        Toast.makeText(context, inputUsername.text.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun onLoginButtonClick() {
        tryLogin()
    }

    private fun tryLogin() {
        Preferences.basicToken = Credentials.basic(
            inputUsername.text.toString(),
            inputPassword.text.toString()
        )
        userViewModel.getUser()
    }

    private fun observeLogin() {
        userViewModel.userResponse.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                Preferences.user = response.body()!!
                Log.d("Login", "user: " + response.body().toString())
                navigateToExplore()
                (activity as MainActivity).showBottomMenu()
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
}
