// ReactNativeBridge.java
package com.example.flutter_money_lion;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class ReactNativeBridge extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    public ReactNativeBridge(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ReactNativeBridge";
    }

    @ReactMethod
    public void sendMessage(String message, Promise promise) {
        try {
            // Here you would communicate with your Flutter code
            // For now, we'll just echo back the message
            WritableMap params = Arguments.createMap();
            params.putString("message", message);
            promise.resolve(params);
        } catch (Exception e) {
            promise.reject("Error", e.getMessage());
        }
    }

    // Method to send events to React Native
    public void sendEventToReactNative(String eventName, WritableMap params) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, params);
    }
}