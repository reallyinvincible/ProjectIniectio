package app.sparsh.iniectio

import android.os.Bundle
import android.util.Log
import dagger.android.DaggerActivity
import javax.inject.Inject

class AuthActivity : DaggerActivity() {

    private val TAG = "AuthActivity"

    @Inject
    lateinit var string: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        Log.d(TAG, string)
    }

}