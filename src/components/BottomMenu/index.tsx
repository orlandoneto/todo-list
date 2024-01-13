import COLOR from "../../styles/colors";
import React from "react";
import {
  Image,
  Text,
  View,
  ImageSourcePropType,
  StyleProp,
  ViewStyle,
} from "react-native";

interface BottomMenuProps {
  focused?: boolean;
  name?: string;
  nameImage?: ImageSourcePropType | "";
  width?: number;
  height?: number;
  containerStyle?: StyleProp<ViewStyle>;
}

const BottomMenu = ({
  focused,
  name,
  nameImage = "",
  width,
  height,
  containerStyle,
}: BottomMenuProps) => {
  return (
    <View style={[{ alignItems: "center" }, containerStyle]}>
      {nameImage && (
        <Image
          style={{
            width: width,
            height: height,
            tintColor: focused ? COLOR.WHITE : COLOR.WHITE,
          }}
          source={nameImage}
        />
      )}
      <Text
        style={{
          fontSize: 9,
          paddingTop: 3,
          fontWeight: "bold",
          color: focused ? COLOR.WHITE : COLOR.WHITE,
        }}
      >
        {name}
      </Text>
    </View>
  );
};

export default BottomMenu;
