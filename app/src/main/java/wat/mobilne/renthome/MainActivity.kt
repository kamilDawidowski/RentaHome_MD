package wat.mobilne.renthome

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.wat.rentahome.MainViewModel
import com.wat.rentahome.MainViewModelFactory
import com.wat.rentahome.models.Offer
import com.wat.rentahome.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_explore.view.*
import wat.mobilne.renthome.R.*
import wat.mobilne.renthome.utils.Preferences
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    var flag = true;
    var flagLanguage = true;
    var offers: List<Offer>? = null

    @Override
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {


        return super.onCreateView(name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Preferences.setup(applicationContext)
        initViewModel()
        observeOffers()
//        loadLocate()

        setContentView(layout.activity_main)


        hideBootomMenu()


        //
        changeIconNuberReservation(8)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                id.language -> {
                    if(flagLanguage)
                    {
                        Toast.makeText(this, getString(string.languagePL), Toast.LENGTH_SHORT).show()
                        setLocale(this,"pl")
                        flagLanguage = false;


                    }
                    else{

//                    setLocate("en")

                        Toast.makeText(this, getString(string.languageENG), Toast.LENGTH_SHORT).show()
                        setLocale(this,"en")
                        flagLanguage = true;
//                        recreate()

                    }



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

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                id.exploreFragment -> {
                    //???

                    navController.navigate(R.id.exploreFragment)
                    // Respond to navigation item 1 click
                    true
                }
                id.mapFragment -> {
                    //???

                    navController.navigate(R.id.mapFragment)
                    // Respond to navigation item 1 click
                    true
                }
                id.reservationFragment -> {
                    //???

                    navController.navigate(R.id.reservationFragment)
                    // Respond to navigation item 1 click
                    true
                }
                id.profileFragment -> {
                    //???

                    navController.navigate(R.id.profileFragment)
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
        viewModel.offersResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                offers = response.body()
                Log.d("Offers", "Offers: " + response.body().toString())
            } else {
                // #TODO: Handle server exception
            }
        })
    }

    fun fetchOffers() {
        viewModel.getOffers()
    }

//    private fun loadLocate() {
//        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
//        val language = sharedPreferences.getString("My_Lang", "")
//        if (language != null) {
//            setLocate(language)
//        }
//    }

    fun setLocale(activity: Activity, languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = activity.resources
        val config: Configuration = resources.getConfiguration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.getDisplayMetrics())
    }
   private  fun changeIconNuberReservation(rezerwacje: Int){
       var badge = bottom_navigation.getOrCreateBadge(R.id.reservationFragment)

// An icon only badge will be displayed unless a number is set:
       if(rezerwacje==null)
       {
           badge.isVisible = false
       }
       else{
           badge.isVisible = true
           badge.number = rezerwacje

       }

    }

    private fun hideBootomMenu(){
        bottom_navigation.menu.findItem(R.id.mapFragment).isVisible = false
        bottom_navigation.menu.findItem(R.id.reservationFragment).isVisible = false
        bottom_navigation.menu.findItem(R.id.profileFragment).isVisible = false
        bottom_navigation.menu.findItem(R.id.exploreFragment).isVisible = false
    }
    public fun showBootomMenu(){

            bottom_navigation.menu.findItem(R.id.mapFragment).isVisible = true
            bottom_navigation.menu.findItem(R.id.reservationFragment).isVisible = true
            bottom_navigation.menu.findItem(R.id.profileFragment).isVisible = true
            bottom_navigation.menu.findItem(R.id.exploreFragment).isVisible = true
        }
    }


