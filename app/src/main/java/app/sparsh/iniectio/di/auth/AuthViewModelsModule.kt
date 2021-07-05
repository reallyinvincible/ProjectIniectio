package app.sparsh.iniectio.di.auth

import androidx.lifecycle.ViewModel
import app.sparsh.iniectio.di.ViewModelKey
import app.sparsh.iniectio.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

}