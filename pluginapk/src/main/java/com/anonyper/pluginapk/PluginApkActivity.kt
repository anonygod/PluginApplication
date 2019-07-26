package com.anonyper.pluginapk

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.anonyper.pluginlibrary.PluginActivity
import android.provider.Settings.ACTION_SETTINGS




class PluginApkActivity : PluginActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plugin_apk)
        findViewById<TextView>(R.id.plugin_activity).setOnClickListener {
            val intent = Intent()
            intent.setClass(this@PluginApkActivity, OrderActivity::class.java)
//            intent.action= ACTION_SETTINGS
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
