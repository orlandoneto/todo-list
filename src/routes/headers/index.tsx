import COLOR from "../../styles/colors";
import { HeaderLeftButton, HeaderText } from "./styles";
import { useNavigation } from "@react-navigation/native";
import { StackNavigationProp } from "@react-navigation/stack";
import React from "react";
import { Image } from "react-native";

type RootStackParamList = {
  Home: undefined;
};

type HomeScreenNavigationProp = StackNavigationProp<RootStackParamList, "Home">;

export const headerDefault = () => {
  const navigation: HomeScreenNavigationProp = useNavigation();

  return {
    headerStyle: {
      height: 80,
      backgroundColor: COLOR.SECONDARY_GREEN,
    },
    headerTitle: () => <HeaderText>Simply Tasks</HeaderText>,
    headerLeft: () => (
      <HeaderLeftButton onPress={() => navigation.navigate("Home")}>
        <Image
          source={require("../../assets/svg/bottomMenu/arrowleft.svg")}
          style={{ width: 61, height: 58 }}
        />
      </HeaderLeftButton>
    ),
    headerRight: () => null,
  };
};
