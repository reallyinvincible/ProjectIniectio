package app.sparsh.iniectio.ui.auth

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import app.sparsh.iniectio.SessionManager
import app.sparsh.iniectio.models.User
import app.sparsh.iniectio.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val TAG = "AuthViewModel"


    fun authenticateWithId(userId: Int) {
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int): LiveData<AuthResource<out User>> {
        return authApi.getUser(userId)
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
    }

    fun observeAuthState(): LiveData<AuthResource<out User?>> {
        return sessionManager.getAuthUser()
    }

}