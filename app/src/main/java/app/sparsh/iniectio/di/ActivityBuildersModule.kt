package app.sparsh.iniectio.di

import app.sparsh.iniectio.di.auth.AuthModule
import app.sparsh.iniectio.di.auth.AuthViewModelsModule
import app.sparsh.iniectio.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            AuthViewModelsModule::class,
            AuthModule::class
        ]
    )
    abstract fun contributeAuthActivity(): AuthActivity

}