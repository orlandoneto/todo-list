import BottomMenu from "../components/BottomMenu";
import { Home, Task, NewTask } from "../screens";
import COLOR from "../styles/colors";
import { headerDefault } from "./headers";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { createStackNavigator } from "@react-navigation/stack";
import React from "react";
import { View } from "react-native";

const HomeBottomTab = createBottomTabNavigator();
const StackTab = createStackNavigator();

const PagelessMain = () => <View />;
const PagelessForwarnd = () => <View />;

const StackTabMain = () => {
  return (
    <StackTab.Navigator
      initialRouteName="Home"
      screenOptions={{
        headerShown: true,
      }}
    >
      <StackTab.Screen name="Home" component={Home} options={headerDefault()} />
      <StackTab.Screen name="Task" component={Task} options={headerDefault()} />
      <StackTab.Screen
        name="NewTask"
        component={NewTask}
        options={headerDefault()}
      />
    </StackTab.Navigator>
  );
};

const Tabs = () => {
  return (
    <HomeBottomTab.Navigator
      initialRouteName="StackTabMain"
      tabBarOptions={{
        showLabel: false,
        style: {
          height: 80,
          backgroundColor: COLOR.SECONDARY_GREEN,
        },
        tabBarInactiveTintColor: "white",
        tabBarActiveTintColor: "white",
      }}
    >
      <HomeBottomTab.Screen
        name="StackTabMain"
        component={StackTabMain}
        options={{
          tabBarIcon: ({ focused }) => (
            <BottomMenu width={25} height={25} focused={focused} />
          ),
        }}
      />
      <HomeBottomTab.Screen
        name="PagelessMain"
        component={PagelessMain}
        listeners={({ navigation }) => ({
          tabPress: async (e) => {
            e.preventDefault();
            navigation.navigate("Home");
          },
        })}
        options={{
          tabBarIcon: ({ focused }) => (
            <BottomMenu
              width={40}
              height={40}
              focused={focused}
              nameImage={require("../assets/svg/bottomMenu/home.svg")}
            />
          ),
        }}
      />

      <HomeBottomTab.Screen
        name="PagelessForwarnd"
        component={PagelessForwarnd}
        options={{
          tabBarIcon: ({ focused }) => (
            <BottomMenu width={25} height={25} focused={focused} />
          ),
        }}
      />
    </HomeBottomTab.Navigator>
  );
};

const TabsRoutes = (_) => <Tabs />;

export default TabsRoutes;
