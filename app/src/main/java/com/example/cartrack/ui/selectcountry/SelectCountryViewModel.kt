package com.example.cartrack.ui.selectcountry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cartrack.data.exceptions.GenericException
import com.example.cartrack.data.model.Country
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class SelectCountryViewModel @Inject constructor() : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()

    val errorMessage = MutableLiveData("")

    init {
        getCountries()
    }

    private fun getCountries() {
        getCountriesFromISOCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Country>> {
                override fun onSuccess(countries: List<Country>) {
                    this@SelectCountryViewModel.countries.value = countries
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    errorMessage.value = e.localizedMessage
                }
            })
    }

    private fun getCountriesFromISOCountries(): Single<List<Country>> {
        return Single.fromCallable {
            Locale.getISOCountries()
                .map { isoCountry ->
                    Locale("en", isoCountry)
                }
                .map { locale ->
                    Country(locale.country, locale.displayCountry)
                }.sortedBy {
                    it.name
                }
        }.onErrorResumeNext {
            Single.error(GenericException())
        }
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }
}
