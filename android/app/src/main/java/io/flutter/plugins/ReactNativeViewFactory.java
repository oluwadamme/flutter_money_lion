// ReactNativeViewFactory.java
package com.example.flutter_money_lion;

import android.content.Context;
import android.view.View;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;
import java.util.Map;

public class ReactNativeViewFactory extends PlatformViewFactory {
    public ReactNativeViewFactory() {
        super(StandardMessageCodec.INSTANCE);
    }

    @Override
    public PlatformView create(Context context, int viewId, Object args) {
        final Map<String, Object> creationParams = (Map<String, Object>) args;
        return new ReactNativeView(context, viewId, creationParams);
    }
}