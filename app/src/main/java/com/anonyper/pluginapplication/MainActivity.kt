package com.anonyper.pluginapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.anonyper.pluginlibrary.IPlugin
import com.anonyper.pluginlibrary.PluginManager
import com.anonyper.utillibrary.FileUtils
import com.anonyper.utillibrary.Loger

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.load_apk).setOnClickListener {
            var apkPath = FileUtils.copyAssetAndWrite(this, "pluginapk.apk")
            Loger.i("文件的路径：$apkPath")
            PluginManager.getPluginManager().loadAPk(apkPath)
        }
        findViewById<TextView>(R.id.launch_activity).setOnClickListener {
            var intent = Intent()
            intent.setClass(this, ProxyActivity::class.java)
            intent.putExtra("FROM", IPlugin.FROM_EXTERNAL)
            intent.putExtra("class_name", "com.anonyper.pluginapk.PluginApkActivity")
            startActivity(intent)

        }


    }
}
