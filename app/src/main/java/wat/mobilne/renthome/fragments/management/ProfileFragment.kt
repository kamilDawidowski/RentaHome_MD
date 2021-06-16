package wat.mobilne.renthome.fragments.management

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.utils.ImageProcesser
import wat.mobilne.renthome.utils.Preferences
import wat.mobilne.renthome.viewmodel.OfferViewModel
import wat.mobilne.renthome.viewmodel.UserViewModel


private const val REQUESTE_CODE=42
class ProfileFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // ustawienia danych użytkytkownika
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        setData()
        observeUpdate()
//        observeUploadImage()

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
            //updateUser(chUsername.text.toString(), chEmail.text.toString())
            updateUser(chUsername.text.toString(), chName.text.toString(), chSurname.text.toString(), "temporary description")
            buttonConfirmChange.visibility = View.INVISIBLE
            hideEditInputs()
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

    private fun observeUpdate() {
        userViewModel.updateUserResponse.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                Preferences.user = response.body()!!
                setData()
                Log.d("Login", "user changed: " + response.body().toString())
            }
        })
    }

    private fun updateUser(username: String, name: String, surname: String, description: String) {
        userViewModel.updateUser(username, name, surname, description)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123)
        {
            var bmp=data?.extras?.get("data") as Bitmap
            profileImage.setImageBitmap(bmp)
//            uploadImage(bmp)
        }
    }

    private fun uploadImage(imageBmp: Bitmap) {
        val image = context?.let { ImageProcesser.bitmapToFile(imageBmp, "profileImage", it) }
        val fileBody: RequestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), image)
        val body = MultipartBody.Builder().addFormDataPart("file-type", "profile")
            .addFormDataPart("photo", "image.png", fileBody)
            .build()
//        val requestFile: RequestBody =
//            RequestBody.create(MediaType.parse("multipart/form-data"), image)
//        val fileBody = MultipartBody.Part.createFormData("imageFile", image?.name, requestFile)
        Log.d("image", fileBody.toString())
        val mainActivity = activity as MainActivity
        mainActivity.viewModel.uploadImage(body, "multipart/form-data; boundary=" + body.boundary())
    }

    private fun observeUploadImage() {
        val mainActivity = activity as MainActivity
        mainActivity.viewModel.uploadImageResponse.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                val responseBody = response.body()
                Log.d("Upload", "Uploaded image" + responseBody.toString())
            } else {
                Toast.makeText(context, "ERROR: " + response.code(), Toast.LENGTH_SHORT).show()
                // #TODO: Handle server exception
            }
        })
    }
}

