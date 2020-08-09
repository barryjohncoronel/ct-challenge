package com.example.cartrack.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cartrack.data.network.user.UserFromApi
import com.example.cartrack.data.service.users.UsersService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val usersService: UsersService
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val errorMessage = MutableLiveData("")

    fun getUsers() {
        usersService.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<UserFromApi>> {
                override fun onSuccess(users: List<UserFromApi>) {
                    Timber.e("onSuccess: $users")
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
