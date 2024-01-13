import { NewTask } from "../NewTask";
import { render } from "@testing-library/react-native";
import React from "react";

jest.mock("react-native-select-dropdown", () => ({
  default: jest.fn(),
}));

jest.mock("react-native-vector-icons/FontAwesome", () => ({
  default: jest.fn(),
}));

jest.mock("react-native-flash-message", () => ({
  default: jest.fn(),
}));

jest.mock("react-native-fs", () => ({
  default: jest.fn(),
}));

jest.mock("@react-native-async-storage/async-storage", () =>
  require("@react-native-async-storage/async-storage/jest/async-storage-mock")
);

describe("Teste do componente NewTask", () => {
  it("Verifica se o NewTask foi renderizado", async () => {
    render(<NewTask />);
    expect(<NewTask />).toBeTruthy();
  });
});
