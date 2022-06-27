package com.redcatgames.movies.presentation

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redcatgames.movies.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val view = binding.root
    setContentView(view)

    // binding.text1.text = BuildConfig.APPLICATION_ID
  }

  override fun onBackPressed() {
    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q &&
        isTaskRoot &&
        (supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount
            ?: 0) == 0 &&
        supportFragmentManager.backStackEntryCount == 0) {
      finishAfterTransition()
    } else {
      super.onBackPressed()
    }
  }
}
