package wat.mobilne.renthome

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.Icon
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.AttributeSet
import android.view.Menu
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.get
import androidx.fragment.app.Fragment

import wat.mobilne.renthome.withoutLogin.login.LoginFragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.MapFragment
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wat.rentahome.MainViewModel
import com.wat.rentahome.MainViewModelFactory
import com.wat.rentahome.models.Offer
import com.wat.rentahome.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_explore.*
import wat.mobilne.renthome.R.*
import wat.mobilne.renthome.adapter.AdapterExplore
import wat.mobilne.renthome.afterLogin.explore.ExploreFragment
import wat.mobilne.renthome.afterLogin.explore.ItemData
import wat.mobilne.renthome.afterLogin.profile.ProfileFragment
import wat.mobilne.renthome.afterLogin.reservation.ReservationFragment
import wat.mobilne.renthome.utils.Preferences
import wat.mobilne.renthome.withoutLogin.login.LoginFragmentDirections
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var viewModel: MainViewModel
    private val exploreFragment=ExploreFragment() as Fragment
    private val profileFragment= ProfileFragment() as Fragment
    private val reservationFragment= ReservationFragment() as Fragment
    private val mapFragment=wat.mobilne.renthome.afterLogin.map.MapFragment() as Fragment
    var flag = true;
    var flagLanguage = true;



    var offers : List<Offer>? = null

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
        //Nawigacja dolna

        // Ustawaiamy nasz Acitivity jak tło
        setContentView(layout.activity_main)
        var flag = true;
        var flagLanguage = true;

// górnrny pasek

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                id.language -> {


                        Toast.makeText(
                            this,
                            getString(string.languageENG),
                            Toast.LENGTH_SHORT
                        ).show();
                        setLocate("en")
                        recreate()
                        flagLanguage = false;


                    true
                }
                id.mode -> {

                    if (flag) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        Toast.makeText(
                            this,
                            getString(string.mode_is_changed_dark),
                            Toast.LENGTH_SHORT
                        ).show();
                        flag = false;
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        Toast.makeText(
                            this,
                            getString(string.mode_is_changed_light),
                            Toast.LENGTH_SHORT
                        ).show();
                        flag = true;

                    }

                    true
                }


                else -> false
            }
        }


        bottom_navigation.setOnNavigationItemSelectedListener {
                item ->
            when(item.itemId) {
                id.exploreFragment -> {
                  bottom_navigation.findNavController().navigate(R.id.profileFragment)
                    // Respond to navigation item 1 click
                    true
                }
                id.exploreFragment -> {
                    Toast.makeText(
                        this,
                        getString(string.mode_is_changed_light),
                        Toast.LENGTH_SHORT
                    ).show();
                    // Respond to navigation item 1 click
                    true
                }

                else -> false
            }

        }







        // Wyświetlanie Badge do ofert


    }

    private fun initViewModel(){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }


    private fun observeOffers() {
        viewModel.offersResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                offers = response.body()
                viewModel.getOffers()
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


}