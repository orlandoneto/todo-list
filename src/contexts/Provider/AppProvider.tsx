import React, { createContext, useReducer, Dispatch, ReactNode } from "react";
import { AppReducer, initialState, AppAction, AppState } from "./AppReducer";

type AppContextProps = {
  state: AppState;
  dispatch: Dispatch<AppAction>;
};

const AppContext = createContext<AppContextProps | undefined>(undefined);

export const AppContextProvider = ({ children }: { children: ReactNode }) => {
  const [state, dispatch] = useReducer(AppReducer, initialState);

  return (
    <AppContext.Provider
      value={{
        state,
        dispatch,
      }}
    >
      {children}
    </AppContext.Provider>
  );
};

export { AppContext };
