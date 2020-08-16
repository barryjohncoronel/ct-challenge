package com.example.ct.ui.selectcountry

import androidx.lifecycle.ViewModel
import com.example.ct.util.ViewModelKey
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
