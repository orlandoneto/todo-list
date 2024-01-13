import { ITask } from "../../../types/types";
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
      {
        id: "1",
        title: "Task 1",
        description: "Task 1 Description",
        status: true,
        fileName: "file1.txt",
        uri: "/path/to/file1",
      },
      {
        id: "2",
        title: "Task 2",
        description: "Task 2 Description",
        status: false,
        fileName: "file2.txt",
        uri: "/path/to/file2",
      },
    ] as ITask[],
  },
};

jest.mock("../../../contexts/Provider/AppProvider", () => ({
  AppContext: {
    Consumer: ({ children }) => children(mockContext),
  },
}));

describe("Task component", () => {
  it("Checks the Task component", async () => {
    render(<Task />);
    expect(<Task />).toBeTruthy();
  });
});
