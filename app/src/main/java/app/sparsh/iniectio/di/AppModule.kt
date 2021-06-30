package app.sparsh.iniectio.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import app.sparsh.iniectio.R
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    companion object {

        @Provides
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions()
                .placeholder(R.drawable.ic_baseline_crop_square)
                .error(R.drawable.ic_baseline_error)
        }

        @Provides
        fun provideGlideInstance(
            application: Application,
            requestOptions: RequestOptions
        ): RequestManager {
            return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)
        }

        @Provides
        fun provideAppDrawable(application: Application): Drawable {
            return ContextCompat.getDrawable(application, R.drawable.ic_baseline_login)!!
        }

    }

}