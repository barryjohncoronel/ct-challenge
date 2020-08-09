package com.example.cartrack.ui.login

import androidx.lifecycle.ViewModel
import com.example.cartrack.data.service.login.LoginServiceModule
import com.example.cartrack.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [LoginServiceModule::class])
interface LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}
