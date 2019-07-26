package com.anonyper.pluginlibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * PluginApplication
 * Created by anonyper on 2019/7/24.
 */
public interface IPlugin {
    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;
    void attach(Activity proxyActivity);
    void onCreate(Bundle saceInstanceState);
    void onDestroy();
    void onResume();
    void onStart();
}
