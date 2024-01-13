import "@testing-library/jest-dom";
import "@testing-library/jest-dom/extend-expect";
import { configure } from "@testing-library/react";

configure({ testIdAttribute: "data-name" });

module.exports = {
  preset: "react-native",
  transform: {
    "^.+\\.js$": "babel-jest",
  },
  testIDAttribute: 'testID',

  setupFilesAfterEnv: [
    "@testing-library/jest-native/extend-expect",
    "@testing-library/jest-dom",
  ],
};
