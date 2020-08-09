package com.example.cartrack.ui.users

import androidx.lifecycle.ViewModel
import com.example.cartrack.data.service.users.UsersServiceModule
import com.example.cartrack.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [UsersServiceModule::class])
interface UsersModule {

    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    fun bindMainViewModel(viewModel: UsersViewModel): ViewModel
}
