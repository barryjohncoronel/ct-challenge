package com.example.cartrack.ui.selectcountry.adapter

import com.example.cartrack.data.model.Country

class CountryItemViewModel(country: Country) {

    val country = "${country.code} - ${country.name}"

    val selected = country.selected
}