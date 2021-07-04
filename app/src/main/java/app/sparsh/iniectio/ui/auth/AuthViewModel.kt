package app.sparsh.iniectio.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import app.sparsh.iniectio.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi?) : ViewModel() {

    private val TAG = "AuthViewModel"

    init {
        if (authApi == null) {
            Log.i(TAG, "Auth is null")
        } else {
            Log.i(TAG, "Auth is non null")
        }
    }

}