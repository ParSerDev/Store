package com.parserdev.store

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.parserdev.store.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(com.parserdev.ui_components.R.style.Theme_Store)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
 }