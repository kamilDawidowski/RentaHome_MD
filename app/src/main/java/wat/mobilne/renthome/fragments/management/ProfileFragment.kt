package wat.mobilne.renthome.fragments.management

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import wat.mobilne.renthome.MainActivity
import wat.mobilne.renthome.R
import wat.mobilne.renthome.utils.ImageProcesser
import wat.mobilne.renthome.utils.Preferences
import wat.mobilne.renthome.viewmodel.UserViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


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
            val action = ProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment()
            findNavController().navigate(action)
        }

        btnLogout.setOnClickListener {
            activity?.intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            activity?.intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Preferences.basicToken = null
            startActivity(activity?.intent)
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
//        chEmail.visibility = View.VISIBLE
        chName.visibility = View.VISIBLE
        chSurname.visibility = View.VISIBLE
        buttonConfirmChange.visibility = View.VISIBLE
    }

    private fun hideEditInputs() {
        chUsername.visibility = View.INVISIBLE
//        chEmail.visibility = View.INVISIBLE
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

            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(intent, 21)

            //  Handle image and upload to backend
            val file = savebitmap(bmp)
            val filePart = MultipartBody.Part.createFormData(
                "image", file!!.name, RequestBody.create(
                    MediaType.parse("image/*"), file
                )
            )
            Log.d("Image", filePart.body().toString() + " " + filePart.headers().toString())
            userViewModel.uploadImage(filePart)
        }
    }

    private fun savebitmap(bmp: Bitmap): File? {
        val extStorageDirectory: String = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString()
        var outStream: OutputStream? = null
        // String temp = null;
        var file = File(extStorageDirectory, "temp.png")
        if (file.exists()) {
            file.delete()
            file = File(extStorageDirectory, "temp.png")
        }
        try {
            outStream = FileOutputStream(file)
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream)
            outStream.flush()
            outStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return file
    }
}

