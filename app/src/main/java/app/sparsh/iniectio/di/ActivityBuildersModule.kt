package app.sparsh.iniectio.di

import app.sparsh.iniectio.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAuthActivity(): AuthActivity

}