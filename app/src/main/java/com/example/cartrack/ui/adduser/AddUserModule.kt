package com.example.cartrack.ui.adduser

import androidx.lifecycle.ViewModel
import com.example.cartrack.ui.adduser.service.AddUserServiceModule
import com.example.cartrack.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [AddUserServiceModule::class])
interface AddUserModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddUserViewModel::class)
    fun bindAddUserViewModel(viewModel: AddUserViewModel): ViewModel
}
