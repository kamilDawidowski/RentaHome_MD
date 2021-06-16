package wat.mobilne.renthome.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_user_profile.*
import wat.mobilne.renthome.R
import wat.mobilne.renthome.fragments.offer.ItemDetailFragmentArgs

class UserProfileFragment : Fragment() {
    private val args: UserProfileFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFields()

    }

    private fun loadFields() {
        txtEmail.text = args.user.email
        txtName.text = args.user.name
        txtSurname.text = args.user.surname
        txtUsername.text = args.user.username
    }

}