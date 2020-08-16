package com.example.ct.ui.addusercredentials

import androidx.lifecycle.ViewModel
import com.example.ct.data.service.addusercredentials.AddUserCredentialsServiceModule
import com.example.ct.util.ViewModelKey
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
