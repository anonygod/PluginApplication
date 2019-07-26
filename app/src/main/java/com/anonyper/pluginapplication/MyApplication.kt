package com.anonyper.pluginapplication

import android.app.Application
import com.anonyper.pluginlibrary.PluginManager

/**
 * PluginApplication
 * Created by anonyper on 2019/7/25.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PluginManager.getPluginManager().init(this)
    }
}