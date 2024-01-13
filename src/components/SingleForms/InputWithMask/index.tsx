import {
  Icons,
  Label,
  SectionTextInput,
  TextMask,
  TextSimpleMask,
} from "./styles";
import React from "react";
import { ImageSourcePropType } from "react-native";

interface InputProps {
  value: string;
  onChange: (text: string) => void;
  label?: string;
  iconLeft?: ImageSourcePropType | "";
  iconRight?: ImageSourcePropType | "";
}

const Input = ({
  value,
  onChange,
  label = "",
  iconLeft = "",
  iconRight = "",
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

          <TextMask value={value} onChangeText={onChange} {...rest} />

          {iconRight !== "" && (
            <Icons
              source={iconRight}
              style={{ resizeMode: "stretch", marginRight: 10 }}
            />
          )}
        </SectionTextInput>
      ) : (
        <TextSimpleMask value={value} onChangeText={onChange} {...rest} />
      )}
    </>
  );
};

export default Input;
