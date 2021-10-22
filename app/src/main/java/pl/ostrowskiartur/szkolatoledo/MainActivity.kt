package pl.ostrowskiartur.szkolatoledo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import pl.ostrowskiartur.szkolatoledo.utis.then

class MainActivity : AppCompatActivity() {
    companion object {
        private const val LOGIN = "Login"
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

        supportActionBar?.setDisplayShowTitleEnabled(false)

        FirebaseApp.initializeApp(this)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
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
        if (navController.currentDestination?.label != null && navController.currentDestination!!.label == LOGIN) {
            changeToolbarVisibility(false)
        } else {
            changeToolbarVisibility(true)
            tvTitle?.text = navController.currentDestination?.label
        }
    }

    private fun changeToolbarVisibility(isVisible: Boolean) {
        clToolbar?.visibility = (isVisible then View.VISIBLE ?: View.INVISIBLE)
    }

}