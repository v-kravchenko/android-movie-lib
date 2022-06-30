package com.redcatgames.movies.presentation

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.lifecycle.lifecycleScope
import com.redcatgames.movies.domain.usecase.config.GetUserConfigUseCase
import com.redcatgames.movies.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var userConfigUseCase: GetUserConfigUseCase

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launch { userConfigUseCase().run { setDefaultNightMode(uiDarkMode) } }
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q &&
                isTaskRoot &&
                (supportFragmentManager
                    .primaryNavigationFragment
                    ?.childFragmentManager
                    ?.backStackEntryCount
                    ?: 0) == 0 &&
                supportFragmentManager.backStackEntryCount == 0
        ) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }
}
