package com.teckathon.missingpeopleapp

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class MVVMApplication() : Application(), DIAware {

    /**
     * a function that fires when the application first runs
     */
    override val di by DI.lazy {
        /* bindings */
        import(androidXModule(this@MVVMApplication))
    }
}