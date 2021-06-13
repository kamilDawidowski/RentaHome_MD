package wat.mobilne.renthome.withoutLogin.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import wat.mobilne.renthome.R


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Przycisk Logowania
        var btnLogin = view.findViewById(R.id.loginButton) as Button;
        btnLogin.setOnClickListener() {

            // inputUsername - przchowuje użytkownika
            // inputPassword przechowuje hasło


            // Przekierowanie do Oferty
            Toast.makeText(context,inputUsername.text ,Toast.LENGTH_SHORT).show();
            val action = LoginFragmentDirections.actionLoginFragmentToOfferFragment()
            findNavController().navigate(action)





        }
        // Przycisk Rejestracji
        var btnSignUp = view.findViewById(R.id.textViewSignUp) as TextView;
        btnSignUp.setOnClickListener() {
            val action = LoginFragmentDirections.actionLoginFragmentToRegiserFragment()
            findNavController().navigate(action)
        }




    }


}
