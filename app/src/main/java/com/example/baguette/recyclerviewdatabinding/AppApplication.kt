package com.example.baguette.recyclerviewdatabinding

import android.app.Application
import com.example.baguette.recyclerviewdatabinding.config.MyDebugTree
import com.example.baguette.recyclerviewdatabinding.di.AppComponent
import com.example.baguette.recyclerviewdatabinding.di.AppModule
import com.example.baguette.recyclerviewdatabinding.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import timber.log.Timber


class AppApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
        initStetho()
        initTimber()
        initLeakCanary()
    }

    private fun initDagger(app: AppApplication): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()

    private fun initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        //.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        )
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(MyDebugTree())
        }
    }

    private fun initLeakCanary() {
        LeakCanary.install(this)
    }
}
