package com.example.cartrack.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.cartrack.R
import com.example.cartrack.databinding.ActivityLoginBinding
import com.example.cartrack.util.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    private lateinit var dataBinding: ActivityLoginBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val viewModel: LoginViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this
    }
}
