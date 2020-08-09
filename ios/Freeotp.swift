//
//  FreeOtp.swift
//  rnfreeotp
//
//  Created by Osama Qarem on 7/2/20.
//

@objc(FreeOtp)
class FreeOtp : NSObject {
  open class Code {
      fileprivate(set) open var value: String
      fileprivate(set) open var from: Date
      fileprivate(set) open var to: Date

      fileprivate init(_ value: String, _ from: Date, _ period: Int64) {
          self.value = value
          self.from = from
          self.to = from.addingTimeInterval(TimeInterval(period))
      }
  }
  
  var URI = URIParameters()
    


  
    @objc
    func getTokenPair(_ totpUrl : String, resolver resolve: @escaping RCTPromiseResolveBlock, rejecter reject: RCTPromiseRejectBlock) -> Void{
        guard let urlc = URLComponents(string: totpUrl) else {
          let error = NSError.init(domain: "Invalid URI: -1", code: -1)
          reject("Error", "Invalid URI: -1", error)
          return
        }
        
        if(URI.validateURI(uri: urlc)){
            guard let otp = OTP(urlc: urlc) else {
                let error = NSError.init(domain: "Invalid URLC: -2", code: -2)
                reject("Error", "Invalid URLC: -2", error)
                return
           }
          
           guard let periodString  = urlc.queryItems?.first(where: { $0.name == "period" })?.value else {
                 print("Invalid URL. Missing params")
                 return
           }
             
           guard let period : Int64  = Int64(periodString) else {
               print("Invalid URL. Failed to parse period")
               return
           }
            
          func totp(_ otp: OTP, now: Date) -> Code {
              let c = Int64(now.timeIntervalSince1970) / period
              let i = Date(timeIntervalSince1970: TimeInterval(c * period))
              return Code(otp.code(c), i, period)
           }

            let now = Date()
            let next = now.addingTimeInterval(TimeInterval(period))
            
            let first = totp(otp, now: now)
            let second = totp(otp, now: next)
            
            let firstRemainingSeconds = String(Int(totp(otp, now: now).to.timeIntervalSince(now)))
            let secondRemainingSeconds = String(Int(totp(otp, now: next).to.timeIntervalSince(now)))
            
            let resultDict : Dictionary<String, String> =
              [
                "tokenOne" : first.value,
                "tokenTwo" : second.value,
                "tokenOneExpires" : firstRemainingSeconds,
                "tokenTwoExpires" : secondRemainingSeconds
              ]
          
          resolve(resultDict)
        }else {
          let error = NSError.init(domain: "Invalid URL: -3", code: -3)
            reject("Error", "Invalid URL: -3", error)
        }
    }
}
