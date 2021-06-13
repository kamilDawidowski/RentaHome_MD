package wat.mobilne.renthome

import android.content.ClipData
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Bundle
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
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController



    @Override
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {


        return super.onCreateView(name, context, attrs)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Ustawaiamy nasz Acitivity jak tło
        setContentView(R.layout.activity_main)
        var flag = true;

// górnrny pasek

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {

                R.id.search -> {

                    // Handle search icon press
                    true
                }

                R.id.language -> {
                    Toast.makeText(
                        this,
                        getString(R.string.language_is_changed),
                        Toast.LENGTH_SHORT
                    ).show();

                    true
                }
                R.id.mode -> {

                    if (flag) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        Toast.makeText(
                            this,
                            getString(R.string.mode_is_changed_dark),
                            Toast.LENGTH_SHORT
                        ).show();
                        flag = false;
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        Toast.makeText(
                            this,
                            getString(R.string.mode_is_changed_light),
                            Toast.LENGTH_SHORT
                        ).show();
                        flag = true;

                    }
                    // Handle more item (inside overflow menu) press
                    true
                }

                else -> false
            }
        }

// An icon only badge will be displayed unless a number is set:



        //Dolne Menu
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.menuExplore -> {
                    // Respond to navigation item 1 click


                    true
                }
                R.id.menuOffer -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.menuMap -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.menuProfile -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }


        // Wyświetlanie Badge do ofert
        var badge = bottom_navigation.getOrCreateBadge(R.id.menuOffer)
        badge.isVisible = true
// An icon only badge will be displayed unless a number is set:
        badge.number = 99


    }



}