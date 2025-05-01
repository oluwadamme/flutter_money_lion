// ReactNativeBridge.h
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>

@interface ReactNativeBridge : RCTEventEmitter <RCTBridgeModule>
- (void)sendEventToReactNative:(NSString *)eventName withData:(NSDictionary *)data;
@end