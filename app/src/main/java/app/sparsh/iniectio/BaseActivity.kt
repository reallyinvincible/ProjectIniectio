package app.sparsh.iniectio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import app.sparsh.iniectio.ui.auth.AuthActivity
import app.sparsh.iniectio.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity() {

    private val TAG = "BaseActivity"

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this, { userAuthResource ->
            if (userAuthResource != null) {
                when (userAuthResource.status) {
                    AuthResource.AuthStatus.LOADING -> {
                    }

                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        Log.i(TAG, "Login Success ${userAuthResource.data?.email}")
                    }

                    AuthResource.AuthStatus.ERROR -> {

                    }

                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        navigateToLoginScreen()
                    }
                }
            }
        })

    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }

}