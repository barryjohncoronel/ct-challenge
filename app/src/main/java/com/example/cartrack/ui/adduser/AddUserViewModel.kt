package com.example.cartrack.ui.adduser

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cartrack.data.service.adduser.AddUserService
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddUserViewModel @Inject constructor(
    private val addUserService: AddUserService
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val username = MutableLiveData("")

    val password = MutableLiveData("")

    val addUserErrorMessage = MutableLiveData("")

    val enableAddUser = MediatorLiveData<Boolean>()

    val addUserSuccessful = MutableLiveData(false)

    init {
        enableAddUser.addSource(username) { checkFieldsForLogin() }
        enableAddUser.addSource(password) { checkFieldsForLogin() }
    }

    private fun checkFieldsForLogin() {
        enableAddUser.value = username.value!!.length >= 5 && password.value!!.length >= 8

        addUserErrorMessage.value = ""
    }

    fun addUser() {
        addUserService
            .addUser(username.value!!, password.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    addUserSuccessful.value = true
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    addUserErrorMessage.value = e.localizedMessage
                }
            })
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }

}
