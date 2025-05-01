import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(home: HomePage());
  }
}

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    // Register platform-specific view factories
    if (defaultTargetPlatform == TargetPlatform.android) {
      SystemChannels.platform_views.invokeMethod('create', {'id': 'react_native_view', 'viewType': 'ReactNativeView'});
    }

    return Scaffold(
      appBar: AppBar(title: Text('Flutter with React Native')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('React Native Component Below:'),
            SizedBox(height: 20),
            SizedBox(
              height: 200,
              child:
                  defaultTargetPlatform == TargetPlatform.iOS
                      ? UiKitView(
                        viewType: 'ReactNativeView', // must match the string in `register(factory, withId: ...)`
                        creationParams: {'message': 'Hello from Flutter!'},
                        creationParamsCodec: const StandardMessageCodec(),
                      )
                      : AndroidView(
                        viewType: 'ReactNativeView',
                        creationParams: {'message': 'Hello from Flutter!'},
                        creationParamsCodec: StandardMessageCodec(),
                      ),
            ),
          ],
        ),
      ),
    );
  }
}
