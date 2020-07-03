//
//  FreeOtpBridge.h
//  rnfreeotp
//
//  Created by Osama Qarem on 7/2/20.
//

#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(FreeOtp, NSObject)

RCT_EXTERN_METHOD(
                  getTokenPair: (NSString *)totpUrl
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject
                  )

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end
