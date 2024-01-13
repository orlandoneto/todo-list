import { Preload } from "../screens";
import TabsRoutes from "./tabs";
import { createStackNavigator } from "@react-navigation/stack";
import React from "react";

const InitialStack = createStackNavigator();

const InitialStackScreen = () => {
  return (
    <InitialStack.Navigator
      initialRouteName="Preload"
      screenOptions={{
        headerShown: true,
      }}
    >
      <InitialStack.Screen
        name="Preload"
        component={Preload}
        options={{ headerShown: false }}
      />

      <InitialStack.Screen
        name="Tabs"
        component={TabsRoutes}
        options={{ headerShown: false }}
      />
    </InitialStack.Navigator>
  );
};

const Routes = (_) => <InitialStackScreen />;

export default Routes;
