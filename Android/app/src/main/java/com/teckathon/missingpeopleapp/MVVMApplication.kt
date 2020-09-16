package com.teckathon.missingpeopleapp

import android.app.Application
import com.teckathon.missingpeopleapp.data.database.AppDatabase
import com.teckathon.missingpeopleapp.data.network.Api
import com.teckathon.missingpeopleapp.data.network.NetworkIntercepter
import com.teckathon.missingpeopleapp.data.network.PeopleApi
import com.teckathon.missingpeopleapp.data.repositories.MissingRepository
import com.teckathon.missingpeopleapp.data.preferences.PreferenceProvider
import com.teckathon.missingpeopleapp.data.repositories.UserRepository
import com.teckathon.missingpeopleapp.ui.activities.auth.AuthViewModelFactory
import com.teckathon.missingpeopleapp.ui.fragments.found.InformFoundViewModelFactory
import com.teckathon.missingpeopleapp.ui.fragments.home.MainViewModelFactory
import com.teckathon.missingpeopleapp.ui.fragments.missing.InformMissingViewModelFactory
import com.teckathon.missingpeopleapp.ui.fragments.profile.ProfileViewModelFactory
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

/**
 * Main Application point class
 * @property di LazyDI
 */
class MVVMApplication() : Application(), DIAware {

    /**
     * a function that fires when the application first runs
     */
    override val di by DI.lazy {
        /* bindings */
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkIntercepter(instance()) }
        bind() from singleton { Api(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }

        bind() from provider { ProfileViewModelFactory(instance()) }

        bind() from singleton { PeopleApi(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { MissingRepository(instance(), instance(), instance()) }
        bind() from provider { MainViewModelFactory(instance()) }

        bind() from provider { InformMissingViewModelFactory(instance()) }
        bind() from provider { InformFoundViewModelFactory(instance()) }

    }
}