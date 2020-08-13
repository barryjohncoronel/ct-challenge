package com.example.cartrack.ui.addusercredentials

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.cartrack.R
import com.example.cartrack.databinding.ActivityAddUserCredentialsBinding
import com.example.cartrack.util.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_add_user_credentials.*
import javax.inject.Inject

class AddUserCredentialsActivity : DaggerAppCompatActivity() {

    private lateinit var dataBinding: ActivityAddUserCredentialsBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val credentialsViewModel: AddUserCredentialsViewModel by viewModels {
        factory
    }

    companion object {
        const val REQUEST_CODE = 100
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onStart() {
        super.onStart()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_user_credentials)
        dataBinding.viewModel = credentialsViewModel
        dataBinding.lifecycleOwner = this

        cl_toolbar.setOnClickListener { onBackPressed() }

        credentialsViewModel.addUserCredentialsSuccessful
            .observe(this, Observer { addUserCredentialsSuccessful ->
                if (addUserCredentialsSuccessful) {
                    setResult(Activity.RESULT_OK)
                    onBackPressed()
                }
            })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }
}
