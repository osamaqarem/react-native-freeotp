import { NativeModules } from 'react-native';

type FreeotpType = {
  multiply(a: number, b: number): Promise<number>;
};

const { Freeotp } = NativeModules;

export default Freeotp as FreeotpType;
