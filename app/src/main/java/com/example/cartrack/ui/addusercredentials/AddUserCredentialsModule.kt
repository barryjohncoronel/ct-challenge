package com.example.cartrack.ui.addusercredentials

import androidx.lifecycle.ViewModel
import com.example.cartrack.data.service.addusercredentials.AddUserCredentialsServiceModule
import com.example.cartrack.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [AddUserCredentialsServiceModule::class])
interface AddUserCredentialsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddUserCredentialsViewModel::class)
    fun bindAddUserCredentialsViewModel(viewModel: AddUserCredentialsViewModel): ViewModel
}
