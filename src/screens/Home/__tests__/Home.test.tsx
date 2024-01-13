import { Home } from "../Home";
import { NavigationContainer } from "@react-navigation/native";
import { render, fireEvent } from "@testing-library/react-native";
import React from "react";

const createMockNavigation = () => {
  return {
    navigate: jest.fn(),
  };
};

describe("Home component", () => {
  it("navigates to Home screen", () => {
    const mockNavigation = createMockNavigation();

    const { getByText } = render(
      <NavigationContainer>
        <Home />
      </NavigationContainer>
    );

    const addButton = getByText("Adicionar tarefa");
    expect(addButton).toBeTruthy();

    fireEvent.press(addButton);

    expect(mockNavigation.navigate).toBeTruthy();
  });
});
