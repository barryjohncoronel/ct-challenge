package com.example.ct.ui.users

import androidx.lifecycle.ViewModel
import com.example.ct.data.service.users.UsersServiceModule
import com.example.ct.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [UsersServiceModule::class])
interface UsersModule {

    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    fun bindUsersViewModel(viewModel: UsersViewModel): ViewModel
}
