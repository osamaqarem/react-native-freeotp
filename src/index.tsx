import { NativeModules } from 'react-native';

type TokenPair = {
  /**
   * Current token.
   */
  tokenOne: string;
  /**
   * Next period step token.
   */
  tokenTwo: string;
  /**
   * Seconds until expiry of the current token.
   */
  tokenOneExpires: string;
  /**
   * Seconds until expiry of the next token.
   */
  tokenTwoExpires: string;
};

type FreeotpType = {
  getTokenPair: (url: string) => Promise<TokenPair>;
};

const { FreeOtp } = NativeModules;

export default FreeOtp as FreeotpType;
