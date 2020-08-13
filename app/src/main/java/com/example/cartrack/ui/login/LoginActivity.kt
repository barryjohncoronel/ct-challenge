package com.example.cartrack.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.cartrack.R
import com.example.cartrack.databinding.ActivityLoginBinding
import com.example.cartrack.ui.addusercredentials.AddUserCredentialsActivity
import com.example.cartrack.ui.selectcountry.SelectCountryActivity
import com.example.cartrack.ui.selectcountry.SelectCountryActivity.Companion.EXTRA_SELECTED_COUNTRY
import com.example.cartrack.ui.users.UsersActivity
import com.example.cartrack.util.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    private lateinit var dataBinding: ActivityLoginBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val viewModel: LoginViewModel by viewModels {
        factory
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onStart() {
        super.onStart()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this

        btn_add_user_credentials.setOnClickListener {
            Intent(this, AddUserCredentialsActivity::class.java).also { intent ->
                startActivityForResult(
                    intent,
                    AddUserCredentialsActivity.REQUEST_CODE
                )

                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
            }
        }

        ll_select_country.setOnClickListener {
            Intent(this, SelectCountryActivity::class.java).also { intent ->
                viewModel.selectedCountry.value?.let { selectedCountry ->
                    intent.putExtra(EXTRA_SELECTED_COUNTRY, selectedCountry)
                }

                startActivityForResult(
                    intent,
                    SelectCountryActivity.REQUEST_CODE
                )

                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
            }
        }

        viewModel.loginSuccessful
            .observe(this, Observer { loginSuccessful ->
                if (loginSuccessful) {
                    startActivity(Intent(this, UsersActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
                    finish()
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AddUserCredentialsActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // clear fields
            viewModel.username.value = ""
            viewModel.password.value = ""
            viewModel.loginErrorMessage.value = ""

            Snackbar.make(dataBinding.root, getString(R.string.add_user_credentials_successful), Snackbar.LENGTH_SHORT).show()


        } else if (requestCode == SelectCountryActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            viewModel.selectedCountry.value = data?.getParcelableExtra(EXTRA_SELECTED_COUNTRY)

            viewModel.selectedCountry.value?.let { selectedCountry ->
                tv_selected_country.text = "${selectedCountry.code} - ${selectedCountry.name}"
            }
        }
    }
}
