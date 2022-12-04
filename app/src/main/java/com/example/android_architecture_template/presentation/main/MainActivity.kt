package com.example.android_architecture_template.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.android_architecture_template.R
import com.example.android_architecture_template.databinding.ActivityMainBinding
import com.example.android_architecture_template.domain.extension.allowReads
import com.example.android_architecture_template.presentation.base.preference.Settings
import com.example.android_architecture_template.presentation.datastore.DataStoreManager
import com.example.android_architecture_template.presentation.extension.collectIn
import com.example.android_architecture_template.presentation.extension.setOnReactiveClickListener
import com.example.android_architecture_template.presentation.extension.viewInflateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewInflateBinding(ActivityMainBinding::inflate)
    private val navController: NavController by lazy {
        findNavController(R.id.activityMainChooseHostFragment)
    }
    private var uiStateJob: Job? = null

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUI()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onStop() {
        uiStateJob?.cancel()
        super.onStop()
    }

    override fun onDestroy() {
        if (isTaskRoot && isFinishing) {
            finishAfterTransition()
        }
        super.onDestroy()
    }

    private fun setupUI() {
        lifecycleScope.launch {
            dataStoreManager.themeMode.collectIn(this@MainActivity) { mode ->
                setNightMode(mode)
            }
        }
    }

    private fun setNightMode(mode: Int) {
        allowReads {
            uiStateJob = lifecycleScope.launchWhenStarted {
                dataStoreManager.setThemeMode(mode)
            }
        }
        when (mode) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.activityMainSwitchThemeFab.setImageResource(R.drawable.ic_mode_night_no_black)
                binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
                    setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.activityMainSwitchThemeFab.setImageResource(R.drawable.ic_mode_night_yes_black)
                binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
                    setNightMode(Settings.MODE_NIGHT_DEFAULT)
                }
            }
            else -> {
                binding.activityMainSwitchThemeFab.setImageResource(R.drawable.ic_mode_night_default_black)
                binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
                    setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
        if (AppCompatDelegate.getDefaultNightMode() != mode)
            AppCompatDelegate.setDefaultNightMode(mode)
    }

}