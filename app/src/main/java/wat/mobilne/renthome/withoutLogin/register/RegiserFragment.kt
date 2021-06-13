package wat.mobilne.renthome.withoutLogin.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_regiser.*
import wat.mobilne.renthome.R
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
        //Przycisk Reestracji
        var btnRegister = view.findViewById(R.id.button_Register) as Button;
        btnRegister.setOnClickListener() {
            //Dane po wprowadzeniu do autoryzacji
            inpuUsername.text
            inputEmail.text
            inputPassword.text
            inputPasswordConfirm.text

            // Nawigacja
            val action = RegiserFragmentDirections.actionRegiserFragmentToLoginFragment()
            findNavController().navigate(action)

        }
        // Przycisk Powrotu do logowania
        var btnLogin = view.findViewById(R.id.btnBacktoLogin) as TextView;
        btnLogin.setOnClickListener() {
            //Nawigacja
            val action = RegiserFragmentDirections.actionRegiserFragmentToLoginFragment()
            findNavController().navigate(action)
        }



        super.onViewCreated(view, savedInstanceState)
    }


}