package wat.mobilne.renthome.fragments.management

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.utils.Preferences
private const val REQUESTE_CODE=42
class ProfileFragment : Fragment() {


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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // ustawienia danych użytkytkownika
        setData()
        buttonConfirmChange.visibility = View.INVISIBLE

        btnUpdateProfile.setOnClickListener {
            showEditInputs()
        }
        btnAddPhoto.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 123)
        }

        btnChangePassword.setOnClickListener {
//            val action = ProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment()
//            findNavController().navigate(action)
        }


        buttonConfirmChange.setOnClickListener {

            // Update w bazie danych wprowadzonych parametrów :
            chUsername.text
            chEmail.text
            chName.text
            chSurname.text
            /////


        }

        hideEditInputs()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setData() {
        tUsername.text = Preferences.user.username
        tEmail.text = Preferences.user.email
        tName.text = Preferences.user.name
        tSurname.text = Preferences.user.surname
    }

    private fun showEditInputs() {
        chUsername.visibility = View.VISIBLE
        chEmail.visibility = View.VISIBLE
        chName.visibility = View.VISIBLE
        chSurname.visibility = View.VISIBLE
        buttonConfirmChange.visibility = View.VISIBLE
    }

    private fun hideEditInputs() {
        chUsername.visibility = View.INVISIBLE
        chEmail.visibility = View.INVISIBLE
        chName.visibility = View.INVISIBLE
        chSurname.visibility = View.INVISIBLE
        buttonConfirmChange.visibility = View.INVISIBLE
    }

    private fun changeData() {

    }

    private fun observeUpdate() {
        val mainActivity = activity as MainActivity
        mainActivity.viewModel.updateUserResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Preferences.user = response.body()!!
                Log.d("Login", "user changed: " + response.body().toString())
            } else {
                // #TODO: Handle server exception
            }
        })
    }

    private fun updateUser(username: String, description: String) {
        val mainActivity = activity as MainActivity
        mainActivity.viewModel.updateUser(username, description)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123)
        {
            var bmp=data?.extras?.get("data") as Bitmap
            profileImage.setImageBitmap(bmp)

        }


    }
}

