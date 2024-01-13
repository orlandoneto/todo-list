import ProgressBar from "../../components/ProgressBar";
import COLOR from "../../styles/colors";
import { Container, HeaderArea, HeaderText } from "./styles";
import { useNavigation } from "@react-navigation/native";
import { StackNavigationProp } from "@react-navigation/stack";
import React, { useEffect, useState } from "react";
import LinearGradient from "react-native-linear-gradient";

type RootStackParamList = {
  Tabs: undefined;
};

type TabsScreenNavigationProp = StackNavigationProp<RootStackParamList, "Tabs">;

export const Preload = () => {
  const navigation: TabsScreenNavigationProp = useNavigation();

  const [count, setCount] = useState(10);

  useEffect(() => {
    setCount(25);
    async function checkToken() {
      setCount(50);
      setTimeout(() => {
        navigation.navigate("Tabs");
        setCount(100);
      }, 1000);
    }
    checkToken();
  }, [navigation]);

  return (
    <LinearGradient
      colors={COLOR.RGB_BACKGROUND}
      style={{
        flex: 1,
        width: "100%",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <Container>
        <HeaderArea>
          <HeaderText testID="preload-id">Simply Tasks</HeaderText>
          <ProgressBar
            width={80}
            progressColor={COLOR.SECONDARY_GREEN_PAY}
            progressBorder={COLOR.SECONDARY_GREEN}
            progressBgColor={COLOR.SECONDARY_GREEN}
            progressInfoColor={COLOR.WHITE}
            count={count}
          />
        </HeaderArea>
      </Container>
    </LinearGradient>
  );
};
