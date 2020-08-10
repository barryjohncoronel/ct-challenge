package com.example.cartrack.ui.users

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.cartrack.R
import com.example.cartrack.databinding.ActivityUsersBinding
import com.example.cartrack.ui.map.MapActivity
import com.example.cartrack.ui.users.adapter.UsersAdapter
import com.example.cartrack.util.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_users.*
import javax.inject.Inject

class UsersActivity : DaggerAppCompatActivity() {

    private lateinit var dataBinding: ActivityUsersBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val viewModel: UsersViewModel by viewModels {
        factory
    }

    private var previouslySelectedIndex: Int = -1

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

        viewModel.users
            .observe(this, Observer { users ->
                val adapter = UsersAdapter(users)

                adapter.itemListener = object : UsersAdapter.ItemListener {
                    override fun onExpand(selectedIndex: Int) {
                        if (previouslySelectedIndex == selectedIndex) {
                            // toggle between expand and collapse
                            users[selectedIndex].expanded = !users[selectedIndex].expanded

                            adapter.notifyItemChanged(selectedIndex)
                        } else {
                            // un-expand the previously expanded item
                            if (previouslySelectedIndex != -1 && previouslySelectedIndex < users.size) {
                                users[previouslySelectedIndex].expanded = false
                                adapter.notifyItemChanged(previouslySelectedIndex)
                            }

                            // expand the currently selected item
                            users[selectedIndex].expanded = true
                            adapter.notifyItemChanged(selectedIndex)
                        }

                        previouslySelectedIndex = selectedIndex
                    }

                    override fun onGoToMap(selectedIndex: Int) {
                        val mapIntent = Intent(this@UsersActivity, MapActivity::class.java).also {
                            it.putExtra(MapActivity.EXTRA_USER, users[selectedIndex])
                        }

                        startActivity(mapIntent)
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
                    }
                }

                rv_users.adapter = adapter
            })
    }
}
