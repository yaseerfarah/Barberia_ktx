package eg.com.invive.barberia_ktx.View

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import eg.com.invive.barberia_ktx.Interface.NavDrawer
import eg.com.invive.barberia_ktx.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_drawer_content.*

class MainActivity : AppCompatActivity(),NavDrawer {
    private val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    private val COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    private val LOCATION_PERMISSION_REQUEST_CODE = 1234
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLocationAndPermission()
         navController= Navigation.findNavController(this, R.id.fragment)
        initNavItem()



    }

    private fun getLocationAndPermission() {

        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (ContextCompat.checkSelfPermission(this.applicationContext, FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this.applicationContext,
                COURSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                permissions,
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.isEmpty()) {
           finish()
        }
    }


    private fun initNavItem(){
        navHome.setOnClickListener {
            closeDrawer()
            navController.navigate(R.id.action_global_home) }
    }

    override fun closeDrawer() {
        mainDrawer.closeDrawer(GravityCompat.START)
    }

    override fun openDrawer() {
        mainDrawer.openDrawer(GravityCompat.START)
    }

    override fun isDrawerOpen(): Boolean {
        return mainDrawer.isOpen
    }
}