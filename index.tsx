import { AppRegistry, LogBox } from 'react-native';
import 'react-native-gesture-handler';
import { name as appName } from './app.json';
import App from './src/App';

LogBox.ignoreAllLogs(true);
AppRegistry.registerComponent(appName, () => App);
