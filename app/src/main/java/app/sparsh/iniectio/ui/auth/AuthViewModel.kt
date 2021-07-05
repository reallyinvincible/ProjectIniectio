package app.sparsh.iniectio.ui.auth

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import app.sparsh.iniectio.models.User
import app.sparsh.iniectio.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {
    private val TAG = "AuthViewModel"

    private val authUser = MediatorLiveData<AuthResource<out User?>>()


    fun authenticateWithId(userId: Int) {

        authUser.value = AuthResource.loading(null)

        val source: LiveData<AuthResource<out User>> =
            authApi.getUser(userId)
                .onErrorReturn {
                    return@onErrorReturn User(id = -1)
                }
                .map { user ->
                    if (user.id == -1) {
                        return@map AuthResource.error("Could not authenticate", null)
                    } else {
                        return@map AuthResource.authenticated(user)
                    }
                }
                .subscribeOn(Schedulers.io())
                .toLiveData()


        authUser.addSource(
            source
        ) {
            authUser.value = it
            authUser.removeSource(source)
        }
    }

    fun observeUser(): LiveData<AuthResource<out User?>> {
        return authUser
    }

}