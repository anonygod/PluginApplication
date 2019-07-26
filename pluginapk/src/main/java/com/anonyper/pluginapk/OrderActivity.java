package com.anonyper.pluginapk;

import android.content.Intent;
import android.os.Bundle;
import com.anonyper.pluginlibrary.PluginActivity;

public class OrderActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        findViewById(R.id.order_view).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(OrderActivity.this, PluginApkActivity.class);
            startActivity(intent);
        });
    }
}
