import { Icons, Label, SectionTextInput, Text, TextSimple } from "./styles";
import React
 from "react";
import { ImageSourcePropType } from "react-native";

interface InputProps {
  value: string;
  onChange: (text: string) => void;
  label?: string;
  nameImage?: ImageSourcePropType | "";
  iconRight?: ImageSourcePropType | "";
  iconLeft?: ImageSourcePropType | "";
  placeholder?: string;
  placeholderTextColor?: string;
  autoCapitalize?: "none" | "sentences" | "words" | "characters" | undefined;
}

const Input = ({
  value,
  onChange,
  label = "",
  iconLeft = "",
  iconRight = "",
  placeholder = "",
  placeholderTextColor = "",
  autoCapitalize = "none",
  ...rest
}: InputProps) => {
  return (
    <>
      {label !== "" && <Label>{label}</Label>}
      {iconLeft !== "" || iconRight !== "" ? (
        <SectionTextInput>
          {iconLeft !== "" && (
            <Icons
              source={iconLeft}
              style={{ resizeMode: "stretch", marginLeft: 10 }}
            />
          )}

          <Text
            value={value}
            onChangeText={onChange}
            placeholder={placeholder}
            placeholderTextColor={placeholderTextColor}
            autoCapitalize={autoCapitalize}
            {...rest}
          />

          {iconRight !== "" && (
            <Icons
              source={iconRight}
              style={{ resizeMode: "stretch", marginRight: 10 }}
            />
          )}
        </SectionTextInput>
      ) : (
        <TextSimple
          value={value}
          onChangeText={onChange}
          placeholder={placeholder}
          placeholderTextColor={placeholderTextColor}
          autoCapitalize={autoCapitalize}
          {...rest}
        />
      )}
    </>
  );
};

export default Input;
