package com.example.todo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.work.*
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.delegate.viewBinding
import com.example.todo.util.gone
import com.example.todo.util.visible
import com.example.todo.workmanager.TodoWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //Action for bottom Nav
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navHostFragment.navController
        )


//bottom nav gone
        val navController: NavController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.viewPageFragment -> binding.bottomNavigationView.gone()
                R.id.splashFragment -> binding.bottomNavigationView.gone()
                else -> {
                    binding.bottomNavigationView.visible()
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (Constant.todoInList) {
            startWorkManager()
        } else {
            WorkManager.getInstance(this@MainActivity).cancelAllWork()
        }
    }

    private fun startWorkManager() {
        Log.v("WorkManager", "WellDone")
        val constraint = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()
        val myWorkRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<TodoWorker>(
            10,
            TimeUnit.MINUTES)
          //  .setInitialDelay(60, TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(this@MainActivity).enqueueUniquePeriodicWork(
            "com.example.todo.workmanager.TodoWorker", //Birkaç kez açsanda devam eden işlemin devamını sağlar.
            ExistingPeriodicWorkPolicy.KEEP, myWorkRequest as PeriodicWorkRequest
        )
    }
}

