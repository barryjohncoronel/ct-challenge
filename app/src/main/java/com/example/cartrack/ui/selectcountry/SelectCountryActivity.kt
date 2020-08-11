package com.example.cartrack.ui.selectcountry

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cartrack.R
import com.example.cartrack.data.model.Country
import com.example.cartrack.databinding.ActivitySelectCountryBinding
import com.example.cartrack.ui.selectcountry.adapter.CountriesAdapter
import com.example.cartrack.util.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_select_country.*
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
        const val EXTRA_SELECTED_COUNTRY = "EXTRA_SELECTED_COUNTRY"
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
            .observe(this, Observer { countries ->
                // set selected true when there is a passed Country
                intent.extras?.getParcelable<Country>(EXTRA_SELECTED_COUNTRY)?.let { selectedCountry ->
                    countries
                        .filter { it.code == selectedCountry.code }
                        .map {
                            it.selected = true
                        }
                }

                val adapter = CountriesAdapter(countries)

                adapter.itemListener = object : CountriesAdapter.ItemListener {
                    override fun onSelect(selectedIndex: Int) {
                        // finish the activity immediately once user select a country
                        Intent().also { intent ->
                            val selectedCountry = countries[selectedIndex].also { it.selected = true }

                            intent.putExtra(EXTRA_SELECTED_COUNTRY, selectedCountry)
                            setResult(Activity.RESULT_OK, intent)
                            onBackPressed()
                        }
                    }
                }

                rv_countries.addItemDecoration(
                    DividerItemDecoration(
                        this,
                        LinearLayoutManager.VERTICAL
                    )
                )

                rv_countries.adapter = adapter
            })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }
}
