import { AppContextProvider } from "./contexts/Provider/AppProvider";
import Routes from "./routes";
import COLOR from "./styles/colors";
import { NavigationContainer } from "@react-navigation/native";
import React from "react";
import { StatusBar } from "react-native";
import FlashMessage from "react-native-flash-message";
import { SafeAreaView } from "react-native-safe-area-context";

const App = () => {
  return (
    <NavigationContainer>
      <SafeAreaView style={{ flex: 1, backgroundColor: COLOR.WHITE }}>
        <StatusBar barStyle="dark-content" />
        <AppContextProvider>
          <Routes />
          <FlashMessage
            position="top"
            hideOnPress={true}
            duration={3000}
            hideStatusBar={true}
            floating={false}
          />
        </AppContextProvider>
      </SafeAreaView>
    </NavigationContainer>
  );
};

export default App;
