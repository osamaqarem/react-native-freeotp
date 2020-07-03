package com.reactnativefreeotp

import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.WritableMap
import com.reactnativefreeotp.core.Token
import com.reactnativefreeotp.core.TokenCode


class FreeotpModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "Freeotp"
  }

  @ReactMethod
  fun multiply(totpUrl: String, promise: Promise) {
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
    } catch (Token.TokenUriInvalidException e) {
      e.printStackTrace()
      promise.reject("Error", "RN-FreeOtp: Invalid URL: " + e.getMessage())
    }


  }

  fun debugLogTokenPair(codes: TokenCode, remainingSeconds: Int, remainingSecondsNext: Int) {
    Log.d("time [ " +
      "${codes.getmCode().toString()} ${remainingSeconds.toString()}, " +
      "${codes.getmNext().getmCode().toString()} ${remainingSecondsNext.toString()}" +
      " ]")
  }

}
