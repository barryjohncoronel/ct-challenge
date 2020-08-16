package com.example.ct.ui.login

import androidx.lifecycle.ViewModel
import com.example.ct.data.service.login.LoginServiceModule
import com.example.ct.util.ViewModelKey
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
