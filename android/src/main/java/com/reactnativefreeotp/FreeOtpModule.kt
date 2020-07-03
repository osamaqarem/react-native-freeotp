package com.reactnativefreeotp

import android.util.Log
import com.facebook.react.bridge.*
import com.reactnativefreeotp.core.Token
import com.reactnativefreeotp.core.TokenCode


class FreeOtpModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "FreeOtp"
  }

  @ReactMethod
  fun getTokenPair(totpUrl: String, promise: Promise) {
    try {
      val token = Token(totpUrl)
      val codes: TokenCode = token.generateCodes()
      val now: Long = System.currentTimeMillis()

      val state: Long = codes.getmUntil() - now
      val remainingSeconds = (state / 1000).toInt()

      val stateNext: Long = codes.getmNext().getmUntil() - now
      val remainingSecondsNext = (stateNext / 1000).toInt()

//      debugLogTokenPair(codes, remainingSeconds, remainingSecondsNext)

      val resultMap = Arguments.createMap()
      resultMap.putString(
        "tokenOne", codes.getmCode()
      )
      resultMap.putString(
        "tokenTwo", codes.getmNext().getmCode()
      )
      resultMap.putString(
        "tokenOneExpires", remainingSeconds.toString()
      )
      resultMap.putString(
        "tokenTwoExpires", remainingSecondsNext.toString()
      )

      promise.resolve(resultMap)
    } catch (e: Token.TokenUriInvalidException) {
      e.printStackTrace()
      promise.reject("Error", "RN-FreeOtp: Invalid URL: " + e.message)
    }


  }

  fun debugLogTokenPair(codes: TokenCode, remainingSeconds: Int, remainingSecondsNext: Int) {
    Log.d("time", "[ " +
      "${codes.getmCode()} ${remainingSeconds}, " +
      "${codes.getmNext().getmCode()} ${remainingSecondsNext}" +
      " ]")
  }

}
