import * as React from 'react';
import {Text, View, StyleSheet} from 'react-native';
import FreeOtp from 'react-native-freeotp';

const exampleTotpUrl =
  'otpauth://totp/freeotp:email%40email.com?secret=is2tjzzjs2fnvxxh6ficlu7z5nt5lbu4iwjnlpsng5gxghv6zhktdvds&algorithm=SHA256&digits=6&period=30';

export default function App() {
  const [result, setResult] = React.useState('');

  React.useEffect(() => {
    const getTokenPair = async () => {
      const tokenPair = await FreeOtp.getTokenPair(exampleTotpUrl);
      setResult(JSON.stringify(tokenPair));
    };
    getTokenPair();
  }, []);
  return (
    <View style={styles.container}>
      <Text>Result:</Text>
      <Text>{result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {flex: 1, justifyContent: 'center', alignItems: 'center'},
});
