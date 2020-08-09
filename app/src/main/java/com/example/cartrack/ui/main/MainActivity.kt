package com.example.cartrack.ui.main

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.cartrack.R
import com.example.cartrack.databinding.ActivityMainBinding
import com.example.cartrack.util.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val viewModel: MainViewModel by viewModels {
        factory
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onStart() {
        super.onStart()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this
    }
}
