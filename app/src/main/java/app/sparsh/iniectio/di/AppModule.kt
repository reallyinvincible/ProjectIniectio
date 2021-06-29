package app.sparsh.iniectio.di

import dagger.Module
import dagger.Provides

@Module
class AppModule {

    companion object {

        @Provides
        fun someString(): String {
            return "Hey there"
        }

    }

}