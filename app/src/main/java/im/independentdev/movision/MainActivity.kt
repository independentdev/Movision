package im.independentdev.movision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import im.independentdev.movision.databinding.ActivityMainBinding
import im.independentdev.movision.ui.main.MainFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		binding.lifecycleOwner = this
		
		val navHostFragment: NavHostFragment =
			supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
		val navController: NavController = navHostFragment.navController
		
		val appBarConfiguration = AppBarConfiguration(navController.graph)
		binding.toolbar.setupWithNavController(navController, appBarConfiguration)
		setSupportActionBar(binding.toolbar)
	}
}