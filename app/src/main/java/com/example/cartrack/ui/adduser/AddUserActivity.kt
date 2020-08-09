package com.example.cartrack.ui.adduser

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.cartrack.R
import com.example.cartrack.databinding.ActivityAddUserBinding
import com.example.cartrack.util.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_add_user.*
import javax.inject.Inject

class AddUserActivity : DaggerAppCompatActivity() {

    private lateinit var dataBinding: ActivityAddUserBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val viewModel: AddUserViewModel by viewModels {
        factory
    }

    companion object {
        const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_user)
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this

        cl_toolbar.setOnClickListener { onBackPressed() }

        viewModel.addUserSuccessful
            .observe(this, Observer { addUserSuccessful ->
                if (addUserSuccessful) {
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
