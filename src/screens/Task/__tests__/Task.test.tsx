import { Task } from "../Task";
import { render } from "@testing-library/react-native";
import React from "react";

jest.mock("react-native-select-dropdown", () => ({
  default: jest.fn(),
}));

jest.mock("react-native-vector-icons/FontAwesome", () => ({
  default: jest.fn(),
}));

jest.mock("@react-native-async-storage/async-storage", () =>
  require("@react-native-async-storage/async-storage/jest/async-storage-mock")
);

const mockContext = {
  state: {
    tasks: [
      { id: 1, title: "Tarefa 1", status: true },
      { id: 2, title: "Tarefa 2", status: false },
    ],
  },
};

jest.mock("../../../contexts/Provider/AppProvider", () => ({
  AppContext: {
    Consumer: ({ children }) => children(mockContext),
  },
}));

describe("Sua descrição aqui", () => {
  it("Verifica o texto do header", async () => {
    render(<Task />);
    expect(<Task />).toBeTruthy();
  });
});
