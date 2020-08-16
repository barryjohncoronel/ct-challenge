package com.example.ct.ui.selectcountry.adapter

import com.example.ct.data.model.Country

class CountryItemViewModel(country: Country) {

    val country = "${country.code} - ${country.name}"

    val selected = country.selected
}