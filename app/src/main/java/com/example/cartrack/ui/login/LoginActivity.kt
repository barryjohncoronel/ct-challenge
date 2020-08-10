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
import com.example.cartrack.ui.adduser.AddUserActivity
import com.example.cartrack.ui.selectcountry.SelectCountryActivity
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

        btn_add_user.setOnClickListener {
            startActivityForResult(
                Intent(this, AddUserActivity::class.java),
                AddUserActivity.REQUEST_CODE
            )

            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        }

        ll_select_country.setOnClickListener {
            startActivityForResult(
                Intent(this, SelectCountryActivity::class.java),
                SelectCountryActivity.REQUEST_CODE
            )

            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
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

        if (requestCode == AddUserActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Snackbar.make(dataBinding.root, getString(R.string.add_user_successful), Snackbar.LENGTH_SHORT).show()
        } else if (requestCode == SelectCountryActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // TODO set country here
        }
    }
}
