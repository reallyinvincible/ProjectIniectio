package app.sparsh.iniectio.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import app.sparsh.iniectio.R
import app.sparsh.iniectio.databinding.ActivityAuthBinding
import app.sparsh.iniectio.viewmodels.ViewModelProviderFactory
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    private val TAG = "AuthActivity"

    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, providerFactory)
            .get(AuthViewModel::class.java)

        subscribeObservers()
        initializeUi()
    }

    private fun subscribeObservers() {
        viewModel.observeUser().observe(this, { userAuthResource ->
            if (userAuthResource != null) {
                when (userAuthResource.status) {
                    AuthResource.AuthStatus.LOADING -> {
                        showProgressBar(true)
                    }

                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        Log.i(TAG, "Login Success ${userAuthResource.data?.email}")
                    }

                    AuthResource.AuthStatus.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(
                            this@AuthActivity,
                            userAuthResource.message + "\nDid you enter a number between 1 to 10?",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        showProgressBar(false)
                    }
                }
            }
        })
    }

    fun showProgressBar(isVisible: Boolean) {
        if (isVisible) {
            binding.pbLoading.visibility = View.VISIBLE
        } else {
            binding.pbLoading.visibility = View.INVISIBLE
        }
    }

    private fun initializeUi() {
        requestManager
            .load(R.drawable.ic_baseline_login)
            .into(binding.ivLogo)
        binding.btnAuth.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_auth -> authenticateUser()
        }
    }

    private fun authenticateUser() {
        if (binding.etUserId.text.toString().isEmpty()) {
            return
        }
        viewModel.authenticateWithId(binding.etUserId.text.toString().toInt())
    }


}