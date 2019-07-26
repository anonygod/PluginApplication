package com.anonyper.pluginapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.anonyper.basecomponent.BaseActivity;
import com.anonyper.pluginlibrary.IPlugin;
import com.anonyper.pluginlibrary.PluginApk;
import com.anonyper.pluginlibrary.PluginManager;
import com.anonyper.utillibrary.Loger;

/**
 * PluginApplication
 * Created by anonyper on 2019/7/25.
 */
public class ProxyActivity extends BaseActivity {
    private String className;
    private PluginApk pluginApk;
    private IPlugin iPlugin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra("class_name");
        pluginApk = PluginManager.getPluginManager().getPluginApk();
        launchPluginActivity();
    }

    void launchPluginActivity() {
        if (pluginApk == null) {
            Loger.i("ProxyActivity ===>>: 需要先加载APK");
            return;
        }
        try {
            Class clazz = pluginApk.dexClassLoader.loadClass(className);
            Object object = clazz.newInstance();
            if (object instanceof IPlugin) {
                iPlugin = (IPlugin) object;
                iPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM", IPlugin.FROM_EXTERNAL);
                iPlugin.onCreate(bundle);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void dealWithIntent(Intent intent){
        String classname = null;
        ComponentName componentName = intent.getComponent();
        if (componentName != null) {
            classname = componentName.getClassName();
        }
        Loger.i(classname);
        if (!TextUtils.isEmpty(classname)) {
            intent.setClass(this, ProxyActivity.class);
            intent.putExtra("class_name", classname);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        dealWithIntent(intent);
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        dealWithIntent(intent);
        super.startActivityForResult(intent, requestCode, options);
    }

    @Override
    public ComponentName startService(Intent service) {
        return super.startService(service);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        dealWithIntent(intent);
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (iPlugin != null) {
            iPlugin.onResume();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (iPlugin != null) {
            iPlugin.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iPlugin != null) {
            iPlugin.onDestroy();
        }
    }

    @Override
    public Resources getResources() {
        if (pluginApk != null) {
            return pluginApk.resources;
        }
        return super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        if (pluginApk != null) {
            return pluginApk.assetManager;
        }
        return super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        if (pluginApk != null) {
            return pluginApk.dexClassLoader;
        }
        return super.getClassLoader();
    }
}
