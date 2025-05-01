import Flutter
import UIKit
import React


class ReactNativeViewFactory: NSObject, FlutterPlatformViewFactory {
    
    private var messenger: FlutterBinaryMessenger
    
    init(messenger: FlutterBinaryMessenger) {
        self.messenger = messenger
        super.init()
    }
    
    func create(
        withFrame frame: CGRect,
        viewIdentifier viewId: Int64,
        arguments args: Any?
    ) -> FlutterPlatformView {
        
        let creationParams = args as? [String: Any] ?? [:]
        return ReactNativeView(
            frame: frame,
            viewIdentifier: viewId,
            arguments: creationParams,
            binaryMessenger: messenger
        )
    }

    func createArgsCodec() -> FlutterMessageCodec & NSObjectProtocol {
        return FlutterStandardMessageCodec.sharedInstance()
    }
}


class ReactNativeView: NSObject, FlutterPlatformView {
    
    private var reactRootView: RCTRootView
    
    init(
        frame: CGRect,
        viewIdentifier viewId: Int64,
        arguments args: [String: Any],
        binaryMessenger messenger: FlutterBinaryMessenger
    ) {
        // Set up the bridge if needed
        let jsBundleLocation: URL = RCTBundleURLProvider.sharedSettings()
            .jsBundleURL(forBundleRoot: "index", fallbackResource: nil)

        let bridge = RCTBridge(bundleURL: jsBundleLocation, moduleProvider: nil, launchOptions: nil)
        
        // The name "MyReactComponent" should match your React Native registered component
        self.reactRootView = RCTRootView(
            bridge: bridge!,
            moduleName: "MyReactComponent",
            initialProperties: args
        )

        self.reactRootView.frame = frame
        
        super.init()
    }
    
    func view() -> UIView {
        return reactRootView
    }
}
