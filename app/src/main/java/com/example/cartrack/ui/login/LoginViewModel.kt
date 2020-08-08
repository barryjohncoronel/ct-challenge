package com.example.cartrack.ui.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cartrack.data.db.model.User
import com.example.cartrack.ui.login.service.LoginService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginService: LoginService
) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    var username = MutableLiveData("")

    var password = MutableLiveData("")

    var loginErrorMessage = MutableLiveData("")

    var enableLogin = MediatorLiveData<Boolean>()

    init {
        enableLogin.addSource(username) { checkFieldsForLogin() }
        enableLogin.addSource(password) { checkFieldsForLogin() }
    }

    private fun checkFieldsForLogin() {
        enableLogin.value = username.value!!.isNotBlank() and password.value!!.isNotBlank()

        loginErrorMessage.value = ""
    }

    fun login() {
        loginService
            .login(username.value!!, password.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<User> {
                override fun onSuccess(user: User) {
                    Timber.e("user: $user")
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
