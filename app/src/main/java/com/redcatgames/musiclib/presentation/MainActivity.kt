package com.redcatgames.musiclib.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redcatgames.musiclib.BuildConfig
import com.redcatgames.musiclib.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.text1.text = BuildConfig.APPLICATION_ID
    }
}