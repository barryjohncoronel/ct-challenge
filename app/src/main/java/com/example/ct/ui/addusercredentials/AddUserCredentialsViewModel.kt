package com.example.ct.ui.addusercredentials

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ct.data.service.addusercredentials.AddUserCredentialsService
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddUserCredentialsViewModel @Inject constructor(
    private val addUserCredentialsService: AddUserCredentialsService
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val username = MutableLiveData("")

    val password = MutableLiveData("")

    val addUserCredentialsErrorMessage = MutableLiveData("")

    val enableAddUserCredentials = MediatorLiveData<Boolean>()

    val addUserCredentialsSuccessful = MutableLiveData(false)

    init {
        enableAddUserCredentials.addSource(username) { checkFieldsForLogin() }
        enableAddUserCredentials.addSource(password) { checkFieldsForLogin() }
    }

    private fun checkFieldsForLogin() {
        enableAddUserCredentials.value = username.value!!.length >= 5 && password.value!!.length >= 8

        addUserCredentialsErrorMessage.value = ""
    }

    fun addUserCredentials() {
        addUserCredentialsService
            .addUserCredentials(username.value!!, password.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    addUserCredentialsSuccessful.value = true
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    addUserCredentialsErrorMessage.value = e.localizedMessage
                }
            })
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }

}
