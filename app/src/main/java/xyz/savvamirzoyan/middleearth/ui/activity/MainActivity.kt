package xyz.savvamirzoyan.middleearth.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import xyz.savvamirzoyan.middleearth.R

class MainActivity : AppCompatActivity() {

    private val navController by lazy {
        findNavController(R.id.main_nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<BottomNavigationView>(R.id.bottom_navigation_view).setupWithNavController(
            navController
        )

//        NavigationUI.setupWithNavController(
//            findViewById<BottomNavigationView>(R.id.bottom_navigation_view),
//            navController
//        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.main_nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}