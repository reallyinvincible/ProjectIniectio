package app.sparsh.iniectio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import app.sparsh.iniectio.models.User
import app.sparsh.iniectio.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val cachedUser = MediatorLiveData<AuthResource<out User?>>()

    fun authenticateWithId(source: LiveData<AuthResource<out User>>) {
        cachedUser.value = AuthResource.loading(null)
        cachedUser.addSource(source) {
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
    }

    fun logout() {
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser(): MediatorLiveData<AuthResource<out User?>> {
        return cachedUser
    }

}