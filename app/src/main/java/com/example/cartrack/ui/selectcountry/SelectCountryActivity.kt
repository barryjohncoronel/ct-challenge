package com.example.cartrack.ui.selectcountry

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.cartrack.R
import com.example.cartrack.databinding.ActivitySelectCountryBinding
import com.example.cartrack.util.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_select_country.*
import timber.log.Timber
import javax.inject.Inject

class SelectCountryActivity : DaggerAppCompatActivity() {

    private lateinit var dataBinding: ActivitySelectCountryBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val viewModel: SelectCountryViewModel by viewModels {
        factory
    }

    companion object {
        const val REQUEST_CODE = 101
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onStart() {
        super.onStart()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_country)
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this

        cl_toolbar.setOnClickListener { onBackPressed() }

        viewModel.countries
            .observe(this, Observer {
                Timber.e("bars: $it") // TODO add adapter here
            })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }
}
