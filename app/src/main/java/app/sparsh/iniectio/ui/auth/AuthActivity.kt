package app.sparsh.iniectio.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import app.sparsh.iniectio.R
import app.sparsh.iniectio.databinding.ActivityAuthBinding
import app.sparsh.iniectio.viewmodels.ViewModelProviderFactory
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

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

        setLogo()
    }

    private fun setLogo() {
        requestManager
            .load(R.drawable.ic_baseline_login)
            .into(binding.ivLogo)
    }


}