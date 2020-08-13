package com.example.cartrack.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cartrack.data.model.User
import com.example.cartrack.databinding.ItemUserBinding

class UsersAdapter(private val users: List<User>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    lateinit var itemListener: ItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class UserViewHolder(
        private val itemUserBinding: ItemUserBinding
    ) : RecyclerView.ViewHolder(itemUserBinding.root) {

        fun bind(position: Int) {
            val userItemViewModel = UserItemViewModel(users[position])

            itemUserBinding.viewModel = userItemViewModel

            itemUserBinding.root.setOnClickListener {
                itemListener.onExpand(position)
            }

            itemUserBinding.btnSeeAddressOnMap.setOnClickListener {
                itemListener.onGoToMap(position)
            }

            itemUserBinding.executePendingBindings()
        }
    }

    interface ItemListener {
        fun onExpand(selectedIndex: Int)

        fun onGoToMap(selectedIndex: Int)
    }
}

