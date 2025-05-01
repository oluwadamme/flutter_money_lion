import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class ReactNativeView extends StatefulWidget {
  const ReactNativeView({super.key});

  @override
  _ReactNativeViewState createState() => _ReactNativeViewState();
}

class _ReactNativeViewState extends State<ReactNativeView> {
  static const platform = MethodChannel('com.example.flutter_money_lion/reactnative');
  static const eventChannel = EventChannel('com.example.flutter_money_lion/reactnative_events');
  String _reactMessage = "No message yet";

  @override
  void initState() {
    super.initState();
    _setupEventChannel();
  }

  void _setupEventChannel() {
    eventChannel.receiveBroadcastStream().listen(
      (dynamic event) {
        setState(() {
          _reactMessage = event['message'] ?? "Unknown message";
        });
      },
      onError: (dynamic error) {
        print('Error receiving event: ${error.message}');
      },
    );
  }

  Future<void> _sendMessageToReactNative() async {
    try {
      final Map result = await platform.invokeMethod('sendMessage', {'message': 'Hello from Flutter!'});
      print('Response from React Native: ${result['message']}');
    } on PlatformException catch (e) {
      print('Error: ${e.message}');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('React Native Integration')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('Message from React Native: $_reactMessage'),
            SizedBox(height: 20),
            ElevatedButton(onPressed: _sendMessageToReactNative, child: Text('Send Message to React Native')),
          ],
        ),
      ),
    );
  }
}
