package com.example.cartrack.ui.selectcountry

import androidx.lifecycle.ViewModel
import com.example.cartrack.data.service.login.LoginServiceModule
import com.example.cartrack.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SelectCountryModule {

    @Binds
    @IntoMap
    @ViewModelKey(SelectCountryViewModel::class)
    fun bindSelectCountryViewModel(viewModel: SelectCountryViewModel): ViewModel
}
