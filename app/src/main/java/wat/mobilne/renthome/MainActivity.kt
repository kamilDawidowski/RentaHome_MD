package wat.mobilne.renthome

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import wat.mobilne.renthome.models.Offer
import wat.mobilne.renthome.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*
import wat.mobilne.renthome.R.*
import wat.mobilne.renthome.utils.Preferences
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    var offers: MutableLiveData<List<Offer>> = MutableLiveData()

    @Override
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {


        return super.onCreateView(name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Preferences.setup(applicationContext)
        initViewModel()
        observeOffers()
        loadLocate()
        setContentView(layout.activity_main)
        var flag = true

        observeToken()
        val token = FirebaseMessaging.getInstance().token
        Log.d("Token", "old token: ${token}")


        //
        changeIconNuberReservation(8)

        val navHostFragment =
            supportFragmentManager.findFragmentById(id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                id.languagePL -> {
                    Toast.makeText(this, getString(string.languagePL), Toast.LENGTH_SHORT).show()
                    setLocate("pl")
                    recreate()
                    true
                }
                id.languageENG -> {
                    Toast.makeText(this, getString(string.languageENG), Toast.LENGTH_SHORT).show()
                    setLocate("en")
                    recreate()
                    true
                }
                id.darkmode -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        Toast.makeText(
                            this,
                            getString(string.mode_is_changed_dark),
                            Toast.LENGTH_SHORT
                        ).show();

                    true
                }
                id.ligthmode -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Toast.makeText(
                        this,
                        getString(string.mode_is_changed_light),
                        Toast.LENGTH_SHORT
                    ).show();



                    true
                }
                else -> false
            }
        }

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                id.exploreFragment -> {
                    //???

                    navController.navigate(id.exploreFragment)
                    // Respond to navigation item 1 click
                    true
                }
                id.mapFragment -> {
                    //???

                    navController.navigate(id.mapFragment)
                    // Respond to navigation item 1 click
                    true
                }
                id.reservationFragment -> {
                    //???

                    navController.navigate(id.reservationFragment)
                    // Respond to navigation item 1 click
                    true
                }
                id.profileFragment -> {
                    //???

                    navController.navigate(id.profileFragment)
                    // Respond to navigation item 1 click
                    true
                }
                else -> false
            }
        }
    }

    private fun initViewModel() {
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }


    private fun observeOffers() {
        viewModel.offersResponse.observe(this, { response ->
            if (response.isSuccessful) {
                offers.value = response.body()
                Log.d("Offers", "Offers: " + response.body().toString())
            } else {
                // #TODO: Handle server exception
            }
        })
    }

    private fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        if (language != null) {
            setLocate(language)
        }
    }

    private fun setLocate(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun changeIconNuberReservation(rezerwacje: Int) {
        var badge = bottom_navigation.getOrCreateBadge(id.reservationFragment)

// An icon only badge will be displayed unless a number is set:
        if (rezerwacje == null) {
            badge.isVisible = false
        } else {
            badge.isVisible = true
            badge.number = rezerwacje

        }

    }

    fun hideBottomMenu() {
        bottom_navigation.menu.findItem(id.mapFragment).isVisible = false
        bottom_navigation.menu.findItem(id.reservationFragment).isVisible = false
        bottom_navigation.menu.findItem(id.profileFragment).isVisible = false
        bottom_navigation.menu.findItem(id.exploreFragment).isVisible = false
    }

    fun showBottomMenu() {

        bottom_navigation.menu.findItem(id.mapFragment).isVisible = true
        bottom_navigation.menu.findItem(id.reservationFragment).isVisible = true
        bottom_navigation.menu.findItem(id.profileFragment).isVisible = true
        bottom_navigation.menu.findItem(id.exploreFragment).isVisible = true
    }

    private fun observeToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                FirebaseMessaging.getInstance().subscribeToTopic("Main");
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Preferences.fcmToken = token

            // Log and toast
            Log.d("Token", "Token: ${token}")
        })
    }

}


