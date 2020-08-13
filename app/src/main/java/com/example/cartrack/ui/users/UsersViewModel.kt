package com.example.cartrack.ui.users

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cartrack.R
import com.example.cartrack.data.model.User
import com.example.cartrack.data.service.users.UsersService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val context: Context,
    private val usersService: UsersService
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val errorMessage = MutableLiveData("")

    val users = MutableLiveData<List<User>>()

    fun getUsers() {
        usersService.getUsers()
            .apply {
                errorMessage.value = context.getString(R.string.please_wait)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<User>> {
                override fun onSuccess(users: List<User>) {
                    errorMessage.value = ""

                    this@UsersViewModel.users.value = users
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    errorMessage.value = e.localizedMessage
                }
            })
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }
}
