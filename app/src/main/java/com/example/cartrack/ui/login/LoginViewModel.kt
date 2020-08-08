package com.example.cartrack.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    var compositeDisposable = CompositeDisposable()

    var username = MutableLiveData("12344444444")

    var password = MutableLiveData("12345")

    var loginErrorMessage = MutableLiveData("")

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }

}
