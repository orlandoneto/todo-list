import { Div } from "./styles";
import React, { ReactNode } from "react";

interface BoxProps {
  margin?: number | null | undefined;
  children: ReactNode;
}

const Box = ({ margin, children }: BoxProps) => {
  const parsedMargin = typeof margin === "number" ? margin : 0;

  return <Div margin={parsedMargin}>{children}</Div>;
};

export default Box;
