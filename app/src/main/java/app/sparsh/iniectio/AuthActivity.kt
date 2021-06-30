package app.sparsh.iniectio

import android.graphics.drawable.Drawable
import android.os.Bundle
import app.sparsh.iniectio.databinding.ActivityAuthBinding
import com.bumptech.glide.RequestManager
import dagger.android.DaggerActivity
import javax.inject.Inject

class AuthActivity : DaggerActivity() {


    private lateinit var binding: ActivityAuthBinding

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLogo()
    }

    private fun setLogo() {
        requestManager
            .load(R.drawable.ic_baseline_login)
            .into(binding.ivLogo)
    }

}