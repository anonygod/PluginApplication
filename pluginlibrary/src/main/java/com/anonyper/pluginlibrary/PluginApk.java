package com.anonyper.pluginlibrary;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import dalvik.system.DexClassLoader;

/**
 * 插件化APK的插件对象
 * PluginApplication
 * Created by anonyper on 2019/7/24.
 */
public class PluginApk {
    public DexClassLoader dexClassLoader;
    public Resources resources;
    public PackageInfo packageInfo;
    public AssetManager assetManager;

    public PluginApk(DexClassLoader dexClassLoader, Resources resources, PackageInfo packageInfo, AssetManager assetManager) {
        this.dexClassLoader = dexClassLoader;
        this.resources = resources;
        this.packageInfo = packageInfo;
        this.assetManager = assetManager;
    }
}
