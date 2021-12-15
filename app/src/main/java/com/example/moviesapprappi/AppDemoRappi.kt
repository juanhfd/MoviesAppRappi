package com.example.moviesapprappi

import android.app.Application
import com.example.moviesapprappi.model.network.GlobalResponseOperator
import com.facebook.stetho.Stetho
import com.skydoves.sandwich.SandwichInitializer
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AppDemoRappi : Application() {

    override fun onCreate() {
        super.onCreate()

        // initialize global sandwich operator
        SandwichInitializer.sandwichOperator = GlobalResponseOperator<Any>(this)


        Stetho.initializeWithDefaults(this)
    }
}
