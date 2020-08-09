package com.example.cartrack.ui.users

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.cartrack.R
import com.example.cartrack.databinding.ActivityUsersBinding
import com.example.cartrack.util.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class UsersActivity : DaggerAppCompatActivity() {

    private lateinit var dataBinding: ActivityUsersBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val viewModel: UsersViewModel by viewModels {
        factory
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onStart() {
        super.onStart()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_users)
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this

        viewModel.getUsers()
    }
}
