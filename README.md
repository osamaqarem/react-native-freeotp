# react-native-freeotp

Generates the same TOTP tokens as [FreeOTP](https://freeotp.github.io/) from your React Native app.

## Installation

```sh
npm install react-native-freeotp
```

### Android

Rebuild the app.

### iOS

Install the pod, then rebuild the app.

`npx pod-install`

## Usage

```js
import Freeotp from 'react-native-freeotp';

// ...

const result = await Freeotp.multiply(3, 7);
```

## Props

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
