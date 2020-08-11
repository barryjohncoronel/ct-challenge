package com.example.cartrack.ui.login

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cartrack.data.model.Country
import com.example.cartrack.data.model.User
import com.example.cartrack.data.service.login.LoginService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val context: Context,
    private val loginService: LoginService
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val username = MutableLiveData("barry")

    val password = MutableLiveData("password")

    val loginErrorMessage = MutableLiveData("")

    val enableLogin = MediatorLiveData<Boolean>()

    val loginSuccessful = MutableLiveData(false)

    val selectedCountry = MutableLiveData<Country>()

    init {
        enableLogin.addSource(username) { checkFieldsForLogin() }
        enableLogin.addSource(password) { checkFieldsForLogin() }
        enableLogin.addSource(selectedCountry) { checkFieldsForLogin() }
    }

    private fun checkFieldsForLogin() {
        enableLogin.value = username.value!!.isNotBlank()
                && password.value!!.isNotBlank()
                && null != selectedCountry.value

        loginErrorMessage.value = ""
    }

    fun login() {
        loginService
            .login(username.value!!, password.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<User> {
                override fun onSuccess(user: User) {
                    loginSuccessful.value = true
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    loginErrorMessage.value = e.localizedMessage
                }
            })
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }

}
