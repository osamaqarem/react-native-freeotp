# react-native-freeotp

Native module for generating the same TOTP tokens as [FreeOTP](https://freeotp.github.io/) from your React Native app.

## Installation

```sh
npm install react-native-freeotp
```

### Android

Rebuild the app.

### iOS

Install the pod, then rebuild the app.

```sh
npx pod-install
```

## Usage

```js
import FreeOtp from 'react-native-freeotp';

const tokenPair = await FreeOtp.getTokenPair(totpUrl);
```

## Types

Result:

```ts
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
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
