package pl.ostrowskiartur.szkolatoledo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.content_main.*
import pl.ostrowskiartur.szkolatoledo.utis.then

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val LOGIN = "Login"
        private const val REGISTER = "Register"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.findNavController()

        updateToolbar()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        FirebaseApp.initializeApp(this)

        initNavigationView()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initNavigationView() {
        tvLogout?.setOnClickListener(this)
    }

    override fun onBackPressed() {
        if (findNavController(R.id.nav_host_fragment_content_main).currentDestination!!.id == R.id.homeFragment) {
            return;
        }
        super.onBackPressed();
    }

    fun changeProgressBarVisibility(isVisible: Boolean) {
        progressBar?.visibility = (isVisible then View.VISIBLE ?: View.GONE)
    }

    fun navigate(resId: Int) {
        findNavController(R.id.nav_host_fragment_content_main).navigate(resId)
    }

    fun updateToolbar() {
        if (navController.currentDestination?.label != null && (navController.currentDestination!!.label == LOGIN || navController.currentDestination!!.label == REGISTER)) {
            changeToolbarVisibility(false)
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            changeToolbarVisibility(true)
            tvTitle?.text = navController.currentDestination?.label
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }

    private fun changeToolbarVisibility(isVisible: Boolean) {
        clToolbar?.visibility = (isVisible then View.VISIBLE ?: View.INVISIBLE)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            tvLogout?.id -> {
                FirebaseAuth.getInstance().signOut()
                navController.navigateUp()
                navController.navigate(R.id.loginFragment)
                drawer_layout.closeDrawer(GravityCompat.START)
            }
        }
    }

}