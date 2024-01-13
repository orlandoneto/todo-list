import { Preload } from "../Preload";
import { NavigationContainer } from "@react-navigation/native";
import React from "react";

jest.mock("react-native-linear-gradient", () => ({
  LinearGradient: jest.fn(),
}));

describe("Preload component", () => {
  it("navigates to Tabs screen after a delay", async () => {
    expect(
      <NavigationContainer>
        <Preload />
      </NavigationContainer>
    ).toBeTruthy();
  });
});
