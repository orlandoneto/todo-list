import { Preload } from "../Preload";
import { NavigationContainer } from "@react-navigation/native";
import { render, fireEvent } from "@testing-library/react-native";
import React from "react";

const createMockNavigation = () => {
  return {
    navigate: jest.fn(),
  };
};

jest.mock("react-native-linear-gradient", () => ({
  default: jest.fn(),
}));

describe("Preload component", () => {
  it("navigates to Preload screen", () => {
    const mockNavigation = createMockNavigation();

    const { getByText } = render(
      <NavigationContainer>
        <Preload />
      </NavigationContainer>
    );

    const addButton = getByText("Simply Tasks");
    expect(addButton).toBeTruthy();

    fireEvent.press(addButton);

    expect(mockNavigation.navigate).toBeTruthy();
  });
});
