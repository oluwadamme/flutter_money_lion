// ReactNativeBridge.m
#import "ReactNativeBridge.h"

@implementation ReactNativeBridge

RCT_EXPORT_MODULE();

- (NSArray<NSString *> *)supportedEvents {
  return @[@"EventToReactNative"];
}

RCT_EXPORT_METHOD(sendMessage:(NSString *)message 
                  resolver:(RCTPromiseResolveBlock)resolve 
                  rejecter:(RCTPromiseRejectBlock)reject) {
  // Here you would communicate with your Flutter code
  // For now, we'll just echo back the message
  NSDictionary *response = @{@"message": message};
  resolve(response);
}

- (void)sendEventToReactNative:(NSString *)eventName withData:(NSDictionary *)data {
  [self sendEventWithName:eventName body:data];
}

@end