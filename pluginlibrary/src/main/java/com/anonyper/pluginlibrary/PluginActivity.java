package com.anonyper.pluginlibrary;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

/**
 * PluginApplication
 * Created by anonyper on 2019/7/25.
 */
public class PluginActivity extends AppCompatActivity implements IPlugin {
    private int from = FROM_INTERNAL;
    private Activity proxyActivity;

    @Override
    public void attach(Activity proxyActivity) {
        this.proxyActivity = proxyActivity;
    }

    @Override
    public void onCreate(Bundle saceInstanceState) {
        if (saceInstanceState != null) {
            from = saceInstanceState.getInt("FROM");
        }
        if (from == FROM_INTERNAL) {
            super.onCreate(saceInstanceState);
            proxyActivity = this;
        }
    }

    @Override
    public void setContentView(View view) {
        if (from == FROM_INTERNAL) {
            super.setContentView(view);
        } else {
            proxyActivity.setContentView(view);
        }
    }

    @Override
    public void setContentView(int layoutResId) {
        if (from == FROM_INTERNAL) {
            super.setContentView(layoutResId);
        } else {
            proxyActivity.setContentView(layoutResId);
        }
    }

    @Override
    public <T extends View> T findViewById(int resId) {
        if (from == FROM_INTERNAL) {
            return super.findViewById(resId);
        } else {
            return proxyActivity.findViewById(resId);
        }
    }

    @Override
    public void onDestroy() {
        if (from == FROM_INTERNAL) {
            super.onDestroy();
        }
        //自行做一些处理
    }

    @Override
    public void onResume() {
        if (from == FROM_INTERNAL) {
            super.onResume();
        }
        //自行做一些处理
    }

    @Override
    public void onStart() {
        if (from == FROM_INTERNAL) {
            super.onStart();
        }
        //自行做一些处理
    }

    @Override
    public Intent getIntent() {
        if (from == FROM_INTERNAL) {
            return super.getIntent();
        }
        return proxyActivity.getIntent();
    }

    @Override
    public void startActivity(Intent intent) {
        if (from == FROM_INTERNAL) {
            super.startActivity(intent);
        } else {
            proxyActivity.startActivity(intent);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (from == FROM_INTERNAL) {
            super.startActivityForResult(intent, requestCode);
        } else {
            proxyActivity.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public String getPackageName() {
        if (from == FROM_INTERNAL) {
            return super.getPackageName();
        } else {
            return proxyActivity.getPackageName();
        }

    }

    @Override
    public File getDir(String name, int mode) {
        if (from == FROM_INTERNAL) {
            return super.getDir(name, mode);
        } else {
            return proxyActivity.getDir(name, mode);
        }

    }

    @Override
    public AssetManager getAssets() {
        if (from == FROM_INTERNAL) {
            return super.getAssets();
        } else {
            return proxyActivity.getAssets();
        }
    }
}
