package com.vikas.vchat

import android.app.Application
import com.vikas.vchat.helper.appModule
import com.vikas.vchat.helper.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VChat: Application() {
    override fun onCreate(){
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@VChat)
            modules(appModule, viewModelModule)
        }
    }
}