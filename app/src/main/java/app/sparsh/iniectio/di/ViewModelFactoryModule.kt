package app.sparsh.iniectio.di

import androidx.lifecycle.ViewModelProvider
import app.sparsh.iniectio.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}