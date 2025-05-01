// ReactNativeView.java
package com.example.flutter_money_lion;


import static java.security.AccessController.getContext;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.facebook.react.ReactRootView;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.react.ReactPackage;
import io.flutter.plugin.platform.PlatformView;

import java.security.AccessController;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReactNativeView implements PlatformView {
    private final ReactRootView reactRootView;
    private final ReactInstanceManager reactInstanceManager;

    ReactNativeView(Context context, int viewId, Map<String, Object> params) {
        reactRootView = new ReactRootView(context);
        Activity activity = new MainActivity();
        Application app = activity.getApplication();

        reactInstanceManager = ReactInstanceManager.builder()
            .setApplication(app)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("index")
            .addPackage(new MainReactPackage())
            .addPackage(new com.example.flutter_money_lion.ReactNativeBridgePackage())
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build();
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // Handle different value types
            if (value instanceof String) {
                bundle.putString(key, (String) value);
            } else if (value instanceof Integer) {
                bundle.putInt(key, (Integer) value);
            } else if (value instanceof Double) {
                bundle.putDouble(key, (Double) value);
            } else if (value instanceof Boolean) {
                bundle.putBoolean(key, (Boolean) value);
            } else if (value instanceof String[]) {
                bundle.putStringArray(key, (String[]) value);
            }
            // Add other types as needed
        }
        // Start React Native with your component
        reactRootView.startReactApplication(
            reactInstanceManager,
            "MyReactComponent",  // Component name in JS
                bundle  // Props to pass to the component
        );
    }

    @Override
    public View getView() {
        return reactRootView;
    }

    @Override
    public void dispose() {
        reactRootView.unmountReactApplication();
    }
}