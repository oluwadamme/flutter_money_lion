import Flutter
import UIKit

public class ReactNativePlugin: NSObject, FlutterPlugin {
    public static func register(with registrar: FlutterPluginRegistrar) {
        let factory = ReactNativeViewFactory(messenger: registrar.messenger())
        registrar.register(factory, withId: "ReactNativeView")
    }
}
