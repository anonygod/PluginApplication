package com.anonyper.pluginlibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import dalvik.system.DexClassLoader;

import java.io.File;
import java.lang.reflect.Method;

/**
 * PluginApplication
 * Created by anonyper on 2019/7/24.
 */
public class PluginManager {
    private static PluginManager pluginManager;

    private PluginManager() {
    }

    public static PluginManager getPluginManager() {
        if (pluginManager == null) {
            pluginManager = new PluginManager();
        }
        return pluginManager;
    }

    private Context context;
    private PluginApk pluginApk;

    public PluginApk getPluginApk() {
        return pluginApk;
    }

    public void init(Context context) {
        this.context = context;
    }

    //加载APK界面
    public void loadAPk(String apkPath){
        if (apkPath == null)
            return;
        if(context ==null){
            throw new RuntimeException("u need load init method first!!!");
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES|PackageManager.GET_SERVICES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return;
        }
        DexClassLoader dexClassLoader = createDexClassLoader(apkPath);
        AssetManager assetManager = createAssetManager(apkPath);
        Resources resources = createResources(assetManager);
        pluginApk = new PluginApk(dexClassLoader, resources, packageInfo, assetManager);
    }


    //创建classLoader
    private DexClassLoader createDexClassLoader(String apkPath) {
        DexClassLoader classLoader = null;
        File file = context.getDir("dex", Context.MODE_PRIVATE);
        classLoader = new DexClassLoader(apkPath, file.getAbsolutePath(), null, context.getClassLoader());
        return classLoader;
    }

    /**
     * 这个assetManager是根据APK path来创建的
     *
     * @param apkPath
     * @return
     */
    private AssetManager createAssetManager(String apkPath) {
        AssetManager assetManager = null;
        try {
            assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(assetManager, apkPath);
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assetManager;

    }

    /**
     * 重新创建了一个resource，而不是用content的（资源不通的）
     *
     * @param assetManager
     * @return
     */
    private Resources createResources(AssetManager assetManager) {
        Resources resources = context.getResources();
        return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
    }
}
